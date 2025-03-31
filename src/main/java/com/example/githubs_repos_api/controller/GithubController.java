package com.example.githubs_repos_api.controller;

import com.example.githubs_repos_api.dto.ErrorResponse;
import com.example.githubs_repos_api.dto.RepositoryResponse;
import com.example.githubs_repos_api.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/**
 * REST Controller that handles HTTP requests related to GitHub repositories.
 * This controller provides endpoints to retrieve repository information for GitHub users.
 * All endpoints in this controller are mapped under the "/api" base path.
 */
@RestController
@RequestMapping("/api")
public class GithubController {

    /**
     * Service that provides GitHub repository operations.
     * This is injected via constructor dependency injection.
     */
    private final GithubService githubService;

    /**
     * Constructor for GithubController.
     * 
     * @param githubService The service that will be used to retrieve GitHub repository information.
     */
    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    /**
     * Endpoint to retrieve non-fork repositories for a specific GitHub user.
     * This endpoint handles GET requests to "/api/repositories/{username}".
     * 
     * @param username The GitHub username whose repositories should be retrieved.
     *                 This is extracted from the URL path variable.
     * @return A ResponseEntity containing a list of RepositoryResponse objects with HTTP status 200 (OK).
     *         Each RepositoryResponse contains repository name, owner login, and branch information.
     */
    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepositoryResponse>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getUserRepositories(username));
    }

    /**
     * Exception handler for when a GitHub user is not found.
     * This method catches HttpClientErrorException.NotFound exceptions thrown when
     * the GitHub API returns a 404 status code (typically when a username doesn't exist).
     * 
     * @param ex The NotFound exception that was thrown.
     * @return A ResponseEntity containing an ErrorResponse with HTTP status 404 (Not Found).
     *         The ErrorResponse includes the status code and an error message.
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HttpClientErrorException.NotFound ex) {
        return ResponseEntity.status(404)
                .body(new ErrorResponse(404, "User not found"));
    }
}