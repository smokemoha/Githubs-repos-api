package com.example.githubs_repos_api.model;

import lombok.Data;

/**
 * Model class representing a GitHub repository.
 * This class maps directly to the JSON structure returned by the GitHub API
 * for repository information.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class Repository {
    /**
     * The name of the repository.
     * This is the repository identifier, not including the owner name.
     */
    private String name;
    
    /**
     * The owner of the repository.
     * This is an object containing information about the user or organization
     * that owns this repository.
     */
    private Owner owner;
    
    /**
     * Flag indicating whether this repository is a fork of another repository.
     * true = this is a forked repository
     * false = this is an original repository
     * 
     * This field is used to filter out forked repositories in the API response.
     */
    private boolean fork;
 }