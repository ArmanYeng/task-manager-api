# Task Manager API

A RESTful Task Manager API built with Java and Spring Boot. The application allows users to create, retrieve, update, and delete tasks through HTTP requests.

## Features

* Create tasks with a title.
* Retrieve all tasks or a specific task by ID.
* Update task titles and completion status.
* Delete tasks.
* Validate incoming requests.
* Return appropriate HTTP status codes for errors.
* Persist tasks using an H2 database.
* Automated tests using JUnit and Spring MockMvc.

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* Maven
* H2 Database
* JUnit
* Spring MockMvc

## API Endpoints

| Method | Endpoint      | Description           |
| ------ | ------------- | --------------------- |
| GET    | `/tasks`      | Retrieve all tasks    |
| GET    | `/tasks/{id}` | Retrieve a task by ID |
| POST   | `/tasks`      | Create a task         |
| PUT    | `/tasks/{id}` | Update a task         |
| DELETE | `/tasks/{id}` | Delete a task         |

## Example Requests

### Create a task

`POST /tasks`

Request body:

```json
{
  "title": "Learn Spring Boot"
}
```

### Update a task

`PUT /tasks/1`

Request body:

```json
{
  "title": "Build a Spring Boot project",
  "completed": true
}
```

## Running the Application

Make sure Java and Maven are available.

On Windows, run the application with:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will start locally. You can then send HTTP requests to `http://localhost:8080/tasks`.

## Running Tests

Execute the automated tests with:

```powershell
.\mvnw.cmd test
```

## Database

The application uses an H2 database with file-based persistence for the main application. Automated tests use a separate in-memory database.

## Future Improvements

* Add pagination and sorting.
* Implement authentication and authorization.
* Add API documentation with OpenAPI/Swagger.
