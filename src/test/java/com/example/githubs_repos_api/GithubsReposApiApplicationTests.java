package com.example.githubs_repos_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Main test class for the GitHub Repositories API application.
 * This class verifies that the Spring application context loads successfully.
 * 
 * The @SpringBootTest annotation configures the test to start the full Spring application context.
 * Unlike the integration tests, this doesn't start an embedded web server by default.
 */
@SpringBootTest
class GithubsReposApiApplicationTests {

	/**
	 * Tests that the application context loads successfully.
	 * 
	 * This is a basic "smoke test" that verifies the Spring context
	 * can start without errors. It ensures that all beans are properly
	 * configured and that there are no issues with dependency injection.
	 * 
	 * If this test passes, it means the basic application configuration is correct.
	 */
	@Test
	void contextLoads() {
		// No assertions needed - the test passes if the context loads without errors
	}

}