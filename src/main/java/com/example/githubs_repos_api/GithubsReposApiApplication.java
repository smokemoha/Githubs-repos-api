package com.example.githubs_repos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main application class for the GitHub Repositories API.
 * This class serves as the entry point for the Spring Boot application.
 * 
 * The @SpringBootApplication annotation is a convenience annotation that adds:
 * - @Configuration: Tags the class as a source of bean definitions
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings
 * - @ComponentScan: Tells Spring to look for components in the current package and below
 */
@SpringBootApplication
public class GithubsReposApiApplication {

	/**
	 * The main method which serves as the entry point for the application.
	 * This method starts the Spring application context and launches the embedded web server.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(GithubsReposApiApplication.class, args);
	}

	/**
	 * Creates and configures a RestTemplate bean to be used for making HTTP requests.
	 * The RestTemplate is used by the GithubService to communicate with the GitHub API.
	 * 
	 * The @Bean annotation tells Spring that this method produces a bean to be managed by Spring.
	 * 
	 * @param builder A RestTemplateBuilder that is automatically provided by Spring Boot
	 * @return A configured RestTemplate instance
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}