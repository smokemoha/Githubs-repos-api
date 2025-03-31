package com.example.githubs_repos_api.dto;

import lombok.Data;

@Data
public class BranchInfo {
    private String name;
    private String lastCommitSha;
}