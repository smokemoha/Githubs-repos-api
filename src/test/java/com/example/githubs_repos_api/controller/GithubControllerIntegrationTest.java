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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenValidUsername_thenReturnsRepositories() {
        ResponseEntity<List<RepositoryResponse>> response = restTemplate.exchange(
                "/api/repositories/octocat",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryResponse>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // We can't assert exact content as it depends on GitHub's state
        // but we can check that the structure is correct
        if (!response.getBody().isEmpty()) {
            RepositoryResponse repo = response.getBody().get(0);
            assertNotNull(repo.getRepositoryName());
            assertNotNull(repo.getOwnerLogin());
            if (!repo.getBranches().isEmpty()) {
                assertNotNull(repo.getBranches().get(0).getName());
                assertNotNull(repo.getBranches().get(0).getLastCommitSha());
            }
        }
    }

    @Test
    void whenInvalidUsername_thenReturns404() {
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(
                "/api/repositories/thisusershouldnotexist404",
                ErrorResponse.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("User not found", response.getBody().getMessage());
    }
}