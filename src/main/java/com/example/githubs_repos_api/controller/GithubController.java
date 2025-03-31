package com.example.githubs_repos_api.controller;

import com.example.githubs_repos_api.dto.ErrorResponse;
import com.example.githubs_repos_api.dto.RepositoryResponse;
import com.example.githubs_repos_api.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GithubController {

    private final GithubService githubService;

    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepositoryResponse>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getUserRepositories(username));
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HttpClientErrorException.NotFound ex) {
        return ResponseEntity.status(404)
                .body(new ErrorResponse(404, "User not found"));
    }
}