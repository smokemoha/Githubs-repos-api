package com.example.githubs_repos_api.model;

import lombok.Data;

/**
 * Model class representing a branch in a GitHub repository.
 * This class maps directly to the JSON structure returned by the GitHub API
 * for branch information.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class Branch {
    /**
     * The name of the branch as returned by the GitHub API.
     * Examples: "main", "master", "smoke", "feature/new-feature"
     */
    private String name;
    
    /**
     * The commit object representing the latest commit on this branch.
     * This contains information about the most recent commit, including its SHA.
     * The Commit class is another model that maps to the GitHub API response.
     */
    private Commit commit;
}