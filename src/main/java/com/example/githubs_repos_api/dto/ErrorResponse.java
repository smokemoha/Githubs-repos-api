package com.example.githubs_repos_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing an error response to be returned to the client.
 * This class is used to provide standardized error information in the API responses
 * when exceptional situations occur, such as when a requested GitHub user doesn't exist.
 * 
 * The class uses Lombok annotations to reduce boilerplate code:
 * - @Data: Generates getters, setters, toString, equals, and hashCode methods
 * - @AllArgsConstructor: Generates a constructor with all fields as parameters
 * - @NoArgsConstructor: Generates a default constructor with no parameters
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    /**
     * The HTTP status code associated with this error.
     * Examples: 404 for "Not Found", 400 for "Bad Request", etc.
     */
    private int status;
    
    /**
     * A descriptive message explaining the error.
     * This provides more details about why the error occurred.
     * Example: "User not found" when a GitHub username doesn't exist.
     */
    private String message;
}