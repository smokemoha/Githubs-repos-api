package com.example.githubs_repos_api.model;

import lombok.Data;

/**
 * Model class representing a commit in a GitHub repository.
 * This class maps directly to the JSON structure returned by the GitHub API
 * for commit information within a branch response.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class Commit {
    /**
     * The SHA-1 hash that uniquely identifies this commit.
     * This is a 40-character hexadecimal string used by Git to identify commits.
     * Example: "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
     * 
     * This field is used to track the last commit on a branch and is one of
     * the key pieces of information returned by the API.
     */
    private String sha;
}