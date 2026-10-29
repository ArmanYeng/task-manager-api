package com.example.taskmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @Operation(summary = "Get all tasks")
  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @Operation(summary = "Create a new task")
  @PostMapping
  public Task createTask(@Valid @RequestBody CreateTaskRequest request) {
    return taskService.createTask(request);
  }

  @Operation(summary = "Get a task by ID")
  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable Long id) {
    return taskService.getTaskById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Operation(summary = "Update a task")
  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
    return taskService.updateTask(id, request)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Operation(summary = "Delete a task")
  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    boolean deleted = taskService.deleteTask(id);

    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Mark a task as completed")
  @PatchMapping("/{id}/complete")
  public Task markTaskAsCompleted(@PathVariable Long id) {
    return taskService.markTaskAsCompleted(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Task not found"));
  }
}
