package com.example.taskmanager;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final List<Task> tasks = new ArrayList<>();
  private Long nextId = 1L;

  public List<Task> getAllTasks() {
    return tasks;
  }

  public Task createTask(CreateTaskRequest request) {
    Task task = new Task(nextId, request.getTitle());

    tasks.add(task);
    nextId++;

    return task;
  }

  public Optional<Task> getTaskById(Long id) {
    return tasks.stream()
    .filter(task -> task.getId().equals(id))
    .findFirst();
  }

  public Optional<Task> updateTask(Long id, UpdateTaskRequest request) {
    Optional<Task> optionalTask = getTaskById(id);

    if(optionalTask.isEmpty()) {
      return Optional.empty();
    }

    Task task = optionalTask.get();

    task.setTitle(request.getTitle());
    task.setCompleted(request.isCompleted());

    return Optional.of(task);
  }

  public boolean deleteTask(Long id) {
    return tasks.removeIf(task -> task.getId().equals(id));
  }
}
