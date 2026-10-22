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

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping
  public Task CreateTask(@RequestBody CreateTaskRequest request) {
    return taskService.createTask(request);
  }

  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable Long id) {
    return taskService.getTaskById(id).orElseThrow(() -> 
  new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
    return taskService.updateTask(id, request)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
