package com.example.taskmanager;

import jakarta.validation.constraints.NotBlank;

public class UpdateTaskRequest {
  @NotBlank(message = "Title must not be blank")
  private String title;
  private boolean completed;

  public UpdateTaskRequest() {

  }

  public UpdateTaskRequest(String title, boolean completed) {
    this.title = title;
    this.completed = completed;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
}
