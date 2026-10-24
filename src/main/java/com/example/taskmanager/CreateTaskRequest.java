package com.example.taskmanager;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskRequest {
  @NotBlank(message = "Title must not be blank")
  private String title;

  public CreateTaskRequest() {

  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
