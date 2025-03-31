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

@Service
public class GithubService {
    private static final String GITHUB_API_URL = "https://api.github.com";
    
    private final RestTemplate restTemplate;
    
    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryResponse> getUserRepositories(String username) {
        String reposUrl = String.format("%s/users/%s/repos", GITHUB_API_URL, username);
        List<Repository> repositories = restTemplate.exchange(
                reposUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Repository>>() {}
        ).getBody();
        return repositories.stream()
                .filter(repo -> !repo.isFork())
                .map(this::mapToRepositoryResponse)
                .collect(Collectors.toList());
    }

    private RepositoryResponse mapToRepositoryResponse(Repository repo) {
        RepositoryResponse response = new RepositoryResponse();
        response.setRepositoryName(repo.getName());
        response.setOwnerLogin(repo.getOwner().getLogin());
        
        String branchesUrl = String.format("%s/repos/%s/%s/branches",
                GITHUB_API_URL, repo.getOwner().getLogin(), repo.getName());
        
        List<Branch> branches = restTemplate.exchange(
                branchesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Branch>>() {}
        ).getBody();

        response.setBranches(branches.stream()
                .map(this::mapToBranchInfo)
                .collect(Collectors.toList()));

        return response;
    }

    private BranchInfo mapToBranchInfo(Branch branch) {
        BranchInfo branchInfo = new BranchInfo();
        branchInfo.setName(branch.getName());
        branchInfo.setLastCommitSha(branch.getCommit().getSha());
        return branchInfo;
    }
}