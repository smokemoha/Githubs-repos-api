package com.example.githubs_repos_api.service;

import com.example.githubs_repos_api.dto.BranchInfo;
import com.example.githubs_repos_api.dto.RepositoryResponse;
import com.example.githubs_repos_api.model.Branch;
import com.example.githubs_repos_api.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for interacting with the GitHub API.
 * This service retrieves repository information for a given GitHub username,
 * filters out forked repositories, and collects branch information for each repository.
 */
@Service
public class GithubService {
    /**
     * Base URL for the GitHub API. All API requests will be made to endpoints under this URL.
     */
    private static final String GITHUB_API_URL = "https://api.github.com";
    
    /**
     * RestTemplate instance used to make HTTP requests to the GitHub API.
     * This is injected via constructor dependency injection.
     */
    private final RestTemplate restTemplate;
    
    /**
     * Constructor for GithubService.
     * 
     * @param restTemplate The RestTemplate bean to use for making HTTP requests to GitHub API.
     */
    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves all non-fork repositories for a given GitHub username.
     * For each repository, it also fetches branch information including the last commit SHA.
     * 
     * @param username The GitHub username to retrieve repositories for.
     * @return A list of RepositoryResponse objects containing repository and branch information.
     */
    public List<RepositoryResponse> getUserRepositories(String username) {
        // Construct the URL for fetching user repositories
        String reposUrl = String.format("%s/users/%s/repos", GITHUB_API_URL, username);
        
        // Make HTTP GET request to GitHub API to retrieve repositories
        // The ParameterizedTypeReference allows proper deserialization of the JSON array response
        List<Repository> repositories = restTemplate.exchange(
                reposUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Repository>>() {}
        ).getBody();
        
        // Process the repositories:
        // 1. Filter out any repositories that are forks
        // 2. Map each repository to a RepositoryResponse object with branch information
        // 3. Collect the results into a list
        return repositories.stream()
                .filter(repo -> !repo.isFork())
                .map(this::mapToRepositoryResponse)
                .collect(Collectors.toList());
    }

    /**
     * Maps a Repository object to a RepositoryResponse DTO.
     * This method also fetches branch information for the repository.
     * 
     * @param repo The Repository object to map.
     * @return A RepositoryResponse object containing repository name, owner login, and branch information.
     */
    private RepositoryResponse mapToRepositoryResponse(Repository repo) {
        // Create a new RepositoryResponse object
        RepositoryResponse response = new RepositoryResponse();
        
        // Set the repository name and owner login
        response.setRepositoryName(repo.getName());
        response.setOwnerLogin(repo.getOwner().getLogin());
        
        // Construct the URL for fetching branch information for this repository
        String branchesUrl = String.format("%s/repos/%s/%s/branches",
                GITHUB_API_URL, repo.getOwner().getLogin(), repo.getName());
        
        // Make HTTP GET request to GitHub API to retrieve branch information
        List<Branch> branches = restTemplate.exchange(
                branchesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Branch>>() {}
        ).getBody();

        // Map each Branch object to a BranchInfo DTO and set it on the response
        response.setBranches(branches.stream()
                .map(this::mapToBranchInfo)
                .collect(Collectors.toList()));

        return response;
    }

    /**
     * Maps a Branch object to a BranchInfo DTO.
     * 
     * @param branch The Branch object to map.
     * @return A BranchInfo object containing the branch name and last commit SHA.
     */
    private BranchInfo mapToBranchInfo(Branch branch) {
        // Create a new BranchInfo object
        BranchInfo branchInfo = new BranchInfo();
        
        // Set the branch name
        branchInfo.setName(branch.getName());
        
        // Set the SHA of the last commit on this branch
        branchInfo.setLastCommitSha(branch.getCommit().getSha());
        
        return branchInfo;
    }
}