package com.example.taskmanager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI taskManagerOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Task Manager API")
            .version("1.0.0")
            .description(
                "a Rest Api for creating, retrieving, updating, completing, and deleting tasks. "
                    + "Built with Java, Spring Boot, and H2 database."));
  }

}
