package com.example.api;

public class ApiClientDemo {
    public static void main(String[] args) {
        // Create a custom retry configuration
        RetryConfig retryConfig = new RetryConfig(
            3,              // maxAttempts
            1000,          // initialDelayMs (1 second)
            10000,         // maxDelayMs (10 seconds)
            2.0            // backoffMultiplier
        );

        // Initialize API client with base URL and retry config
        try (ApiClient apiClient = new ApiClient("https://api.example.com", retryConfig)) {
            // Example GET request
            User user = apiClient.get("/users/1", User.class);
            System.out.println("Retrieved user: " + user.getName());

            // Example POST request
            CreateUserRequest createRequest = new CreateUserRequest("John Doe", "john@example.com");
            User newUser = apiClient.post("/users", createRequest, User.class);
            System.out.println("Created new user: " + newUser.getName());
        } catch (Exception e) {
            System.err.println("API request failed: " + e.getMessage());
        }
    }

    // Example DTOs
    private static class User {
        private String name;
        private String email;

        // Getters and setters needed for Jackson
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    private static class CreateUserRequest {
        private String name;
        private String email;

        public CreateUserRequest(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Getters needed for Jackson
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}