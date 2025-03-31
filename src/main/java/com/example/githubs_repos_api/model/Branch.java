package com.example.githubs_repos_api.model;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private Commit commit;
}