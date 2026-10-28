# Task Manager API

A RESTful Task Manager API built with Java and Spring Boot. This project demonstrates backend development fundamentals, including REST endpoints, database persistence, request validation, centralized exception handling, and automated testing.

## Features

* Create tasks with a title.
* Retrieve all tasks or a specific task by ID.
* Update task titles and completion status.
* Mark tasks as completed using a dedicated PATCH endpoint.
* Delete tasks.
* Validate incoming requests and reject blank task titles.
* Return structured JSON error responses with appropriate HTTP status codes.
* Persist tasks using an H2 database.
* Provide a health-check endpoint.
* Test application behavior using JUnit and Spring MockMvc.

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* Maven
* H2 Database
* JUnit
* Spring MockMvc

## API Endpoints

| Method | Endpoint               | Description                                 |
| ------ | ---------------------- | ------------------------------------------- |
| GET    | `/tasks`               | Retrieve all tasks                          |
| GET    | `/tasks/{id}`          | Retrieve a task by ID                       |
| POST   | `/tasks`               | Create a task                               |
| PUT    | `/tasks/{id}`          | Update a task's title and completion status |
| PATCH  | `/tasks/{id}/complete` | Mark a task as completed                    |
| DELETE | `/tasks/{id}`          | Delete a task                               |
| GET    | `/health`              | Check application health                    |

## Example Requests

### Create a task

**Request**

```http
POST /tasks
Content-Type: application/json
```

```json
{
  "title": "Learn Spring Boot"
}
```

A successful response includes the generated task ID, title, and completion status.

### Retrieve all tasks

```http
GET /tasks
```

### Update a task

**Request**

```http
PUT /tasks/1
Content-Type: application/json
```

```json
{
  "title": "Build a Spring Boot project",
  "completed": true
}
```

### Mark a task as completed

```http
PATCH /tasks/1/complete
```

### Delete a task

```http
DELETE /tasks/1
```

## Error Handling

The API uses appropriate HTTP status codes to communicate errors.

| Status Code       | Meaning                                                       |
| ----------------- | ------------------------------------------------------------- |
| `400 Bad Request` | The request contains invalid data, such as a blank task title |
| `404 Not Found`   | The requested task does not exist                             |

Errors are returned as structured JSON responses.

## Running the Application

### Prerequisites

* Java Development Kit (JDK)
* Git (optional, for cloning the repository)

The project includes the Maven Wrapper, so a separate Maven installation should not be necessary.

### Start the application on Windows

Clone the repository and navigate to the project directory. Then run:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts locally on port `8080`, unless configured otherwise.

You can then send requests to:

```text
http://localhost:8080/tasks
```

## Running Tests

Run the automated test suite on Windows:

```powershell
.\mvnw.cmd test
```

The tests use Spring MockMvc to verify endpoint behavior, request validation, error responses, and task operations.

## Database

The application uses an H2 database with file-based persistence. Automated tests use a separate in-memory database.

## Future Improvements

* Add pagination and sorting.
* Implement authentication and authorization.
* Add API documentation using OpenAPI/Swagger.
* Add integration tests for more edge cases.
