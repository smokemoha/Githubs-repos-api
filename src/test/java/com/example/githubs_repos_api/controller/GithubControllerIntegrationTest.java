package com.example.githubs_repos_api.controller;

import com.example.githubs_repos_api.dto.ErrorResponse;
import com.example.githubs_repos_api.dto.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test class for the GitHub Controller.
 * This class tests the complete flow from HTTP request to response,
 * including the actual calls to the GitHub API.
 * 
 * The @SpringBootTest annotation configures the test to start the full Spring application context
 * and an embedded web server on a random port.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubControllerIntegrationTest {

    /**
     * TestRestTemplate is automatically configured by Spring Boot for testing REST endpoints.
     * It's similar to RestTemplate but with additional testing capabilities.
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Tests the successful retrieval of repositories for a valid GitHub username.
     * This test uses "octocat" as a known valid GitHub user.
     * 
     * The test verifies:
     * 1. The HTTP status code is 200 OK
     * 2. The response body is not null
     * 3. If repositories are returned, they have the expected structure
     */
    @Test
    void whenValidUsername_thenReturnsRepositories() {
        // Make a GET request to the API endpoint with a valid username
        ResponseEntity<List<RepositoryResponse>> response = restTemplate.exchange(
                "/api/repositories/octocat",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryResponse>>() {}
        );

        // Verify the HTTP status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verify the response body is not null
        assertNotNull(response.getBody());
        // We can't assert exact content as it depends on GitHub's state
        // but we can check that the structure is correct
        if (!response.getBody().isEmpty()) {
            RepositoryResponse repo = response.getBody().get(0);
            // Verify repository name is not null
            assertNotNull(repo.getRepositoryName());
            // Verify owner login is not null
            assertNotNull(repo.getOwnerLogin());
            // If branches are returned, verify they have the expected structure
            if (!repo.getBranches().isEmpty()) {
                assertNotNull(repo.getBranches().get(0).getName());
                assertNotNull(repo.getBranches().get(0).getLastCommitSha());
            }
        }
    }

    /**
     * Tests the error handling for an invalid (non-existent) GitHub username.
     * This test uses a username that is highly unlikely to exist.
     * 
     * The test verifies:
     * 1. The HTTP status code is 404 Not Found
     * 2. The response body is not null
     * 3. The error response contains the expected status code (404)
     * 4. The error response contains the expected error message
     */
    @Test
    void whenInvalidUsername_thenReturns404() {
        // Make a GET request to the API endpoint with an invalid username
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(
                "/api/repositories/thisusershouldnotexist404",
                ErrorResponse.class
        );

        // Verify the HTTP status code is 404 Not Found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Verify the response body is not null
        assertNotNull(response.getBody());
        // Verify the error status code is 404
        assertEquals(404, response.getBody().getStatus());
        // Verify the error message is as expected
        assertEquals("User not found", response.getBody().getMessage());
    }
}