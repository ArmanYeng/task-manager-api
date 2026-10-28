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

  @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
  public ResponseEntity<Map<String, String>> handleResponseStatusException(
    org.springframework.web.server.ResponseStatusException exception) {

      HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());

      Map<String, String> error = Map.of(
        "error", status.getReasonPhrase(),
        "message", exception.getReason() != null
        ? exception.getReason()
        : status.getReasonPhrase()
      );

      return ResponseEntity.status(status).body(error);
    }
}
