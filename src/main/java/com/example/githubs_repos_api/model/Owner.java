package com.example.githubs_repos_api.model;

import lombok.Data;

/**
 * Model class representing the owner of a GitHub repository.
 * This class maps directly to the owner object in the JSON structure 
 * returned by the GitHub API for repository information.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class Owner {
    /**
     * The login username of the repository owner.
     * This is the GitHub username of the person or organization that owns the repository.
     * Example: "octocat", "microsoft", "spring-projects","smokemoha"
     * 
     * This field is used to identify the owner in API responses and is also used
     * when constructing URLs for further API requests.
     */
    private String login;
}