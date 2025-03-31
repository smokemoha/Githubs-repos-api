package com.example.githubs_repos_api.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing branch information from a GitHub repository.
 * This class is used to transfer branch data from the service layer to the controller
 * and ultimately to the client in the API response.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class BranchInfo {
    /**
     * The name of the branch (e.g., "main", "master", "smoke").
     * This field represents the branch identifier in the GitHub repository.
     */
    private String name;
    
    /**
     * The SHA-1 hash of the last commit on this branch.
     * This is a 40-character hexadecimal string that uniquely identifies the commit.
     * Example: "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
     */
    private String lastCommitSha;
}