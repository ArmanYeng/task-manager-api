package com.example.taskmanager;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

  private final List<Task> tasks = new ArrayList<>();

  public List<Task> getAllTasks() {
    return tasks;
  }
}
