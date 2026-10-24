package com.example.taskmanager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService (TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task createTask(CreateTaskRequest request) {
    Task task = new Task(null, request.getTitle());

    return taskRepository.save(task);
  }

  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  public Optional<Task> updateTask(Long id, UpdateTaskRequest request) {
    Optional<Task> optionalTask = taskRepository.findById(id);

    if(optionalTask.isEmpty()) {
      return Optional.empty();
    }

    Task task = optionalTask.get();

    task.setTitle(request.getTitle());
    task.setCompleted(request.isCompleted());

    return Optional.of(taskRepository.save(task));
  }

  public boolean deleteTask(Long id) {
    if(!taskRepository.existsById(id)) {
      return false;
    }

    taskRepository.deleteById(id);
    return true;
  }
}
