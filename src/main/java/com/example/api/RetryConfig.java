package com.example.api;

public class RetryConfig {
    private final int maxAttempts;
    private final long initialDelayMs;
    private final long maxDelayMs;
    private final double backoffMultiplier;

    public RetryConfig(int maxAttempts, long initialDelayMs, long maxDelayMs, double backoffMultiplier) {
        this.maxAttempts = maxAttempts;
        this.initialDelayMs = initialDelayMs;
        this.maxDelayMs = maxDelayMs;
        this.backoffMultiplier = backoffMultiplier;
    }

    public static RetryConfig getDefault() {
        return new RetryConfig(3, 1000, 10000, 2.0);
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long getInitialDelayMs() {
        return initialDelayMs;
    }

    public long getMaxDelayMs() {
        return maxDelayMs;
    }

    public double getBackoffMultiplier() {
        return backoffMultiplier;
    }
}