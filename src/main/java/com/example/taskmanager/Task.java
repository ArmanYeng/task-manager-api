package com.example.taskmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private boolean completed;

  public Task() {}

  public Task(Long id, String title) {
    this.id = id;
    this.title = title;
    this.completed = false;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
}
