package com.example.taskmanager;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
    String message = exception.getBindingResult()
    .getFieldErrors()
    .get(0)
    .getDefaultMessage();

    Map<String, String> error = Map.of(
      "error", "Validation failed",
      "message", message
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
