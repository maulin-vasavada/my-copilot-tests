package com.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiClient implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final String baseUrl;
    private final RetryConfig retryConfig;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;

    public ApiClient(String baseUrl, RetryConfig retryConfig) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.retryConfig = retryConfig != null ? retryConfig : RetryConfig.getDefault();
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.createDefault();
    }

    public <T> T get(String endpoint, Class<T> responseType) throws IOException {
        HttpGet request = new HttpGet(baseUrl + endpoint);
        return executeWithRetry(request, responseType);
    }

    public <T> T post(String endpoint, Object body, Class<T> responseType) throws IOException {
        HttpPost request = new HttpPost(baseUrl + endpoint);
        String jsonBody = objectMapper.writeValueAsString(body);
        request.setEntity(new StringEntity(jsonBody));
        request.setHeader("Content-Type", "application/json");
        return executeWithRetry(request, responseType);
    }

    private <T> T executeWithRetry(HttpRequestBase request, Class<T> responseType) throws IOException {
        int attempt = 1;
        long delay = retryConfig.getInitialDelayMs();

        while (true) {
            try {
                return execute(request, responseType);
            } catch (Exception e) {
                if (shouldRetry(e) && attempt < retryConfig.getMaxAttempts()) {
                    logger.warn("Request failed (attempt {}), retrying in {} ms", attempt, delay);
                    sleep(delay);
                    delay = calculateNextDelay(delay);
                    attempt++;
                } else {
                    throw e;
                }
            }
        }
    }

    private <T> T execute(HttpRequestBase request, Class<T> responseType) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return objectMapper.readValue(responseBody, responseType);
            } else {
                throw new IOException("Request failed with status " + statusCode + ": " + responseBody);
            }
        }
    }

    private boolean shouldRetry(Exception e) {
        // Add specific conditions for retry based on exception type
        return e instanceof IOException;
    }

    private long calculateNextDelay(long currentDelay) {
        long nextDelay = (long) (currentDelay * retryConfig.getBackoffMultiplier());
        return Math.min(nextDelay, retryConfig.getMaxDelayMs());
    }

    private void sleep(long delayMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }
}