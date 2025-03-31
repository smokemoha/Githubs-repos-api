package com.example.githubs_repos_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class RepositoryResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfo> branches;
}