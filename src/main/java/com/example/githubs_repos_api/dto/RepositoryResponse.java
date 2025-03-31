package com.example.githubs_repos_api.dto;

import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a GitHub repository in the API response.
 * This class contains the essential information about a repository that will be
 * returned to the client, including repository name, owner login, and branch information.
 * 
 * The @Data annotation from Lombok automatically generates:
 * - Getters and setters for all fields
 * - toString() method
 * - equals() and hashCode() methods
 * - A default constructor
 */
@Data
public class RepositoryResponse {
    /**
     * The name of the GitHub repository.
     * This is the repository identifier, not including the owner name.
     * Example: "spring-boot" (not "spring-projects/spring-boot")
     */
    private String repositoryName;
    
    /**
     * The login username of the repository owner.
     * This is the GitHub username of the person or organization that owns the repository.
     * Example: "octocat"
     */
    private String ownerLogin;
    
    /**
     * A list of branch information for this repository.
     * Each BranchInfo object contains the branch name and the SHA of its last commit.
     * This provides information about all branches in the repository.
     */
    private List<BranchInfo> branches;
}