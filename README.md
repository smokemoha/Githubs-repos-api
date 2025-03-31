# GitHub Repositories API

A Spring Boot application that provides an API to list GitHub repositories for a specified user, excluding forks.

## Features

- Retrieves non-fork repositories for a given GitHub username.
- Provides repository name, owner login, and branch information.
- Returns appropriate error responses for non-existent users.

## Technologies

- **Java 21**
- **Spring Boot 3.x**
- **Maven**

## Setup and Running

### Prerequisites

- Java 21 JDK
- Maven

### Running the Application

1. Clone the repository:
    ```bash
    git clone https://github.com/smokemoha/github-repos-api.git
    ```
2. Navigate to the project directory:
    ```bash
    cd github-repos-api
    ```
3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

The application will start on port `8080`.

### Running Tests

To execute the test suite, run:
```bash
mvn test
```

## API Documentation

### Get User Repositories

Retrieves a list of non-fork repositories for a specified GitHub user.

- **Endpoint**: `GET /api/repositories/{username}`
- **Path Parameters**:
  - `username` (string): GitHub username.

#### Success Response

- **Status Code**: `200 OK`
- **Content**:
  ```json
  [
     {
        "repositoryName": "Hello-World",
        "ownerLogin": "octocat",
        "branches": [
          {
             "name": "main",
             "lastCommitSha": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
          }
        ]
     }
  ]
  ```

#### Error Response

- **Status Code**: `404 Not Found`
- **Content**:
  ```json
  {
     "error": "User not found"
  }
  ```

## Examples

### Request

```bash
GET /api/repositories/octocat
```

### Response

```json
[
  {
     "repositoryName": "Hello-World",
     "ownerLogin": "octocat",
     "branches": [
        {
          "name": "main",
          "lastCommitSha": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
        }
     ]
  }
]
```

This README provides a comprehensive overview of the project, its features, and usage instructions.
