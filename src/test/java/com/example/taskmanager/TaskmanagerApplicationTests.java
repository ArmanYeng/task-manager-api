package com.example.taskmanager;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class TaskmanagerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTasksReturnsOk() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createTaskReturnsCreatedTask() throws Exception {
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Learn Spring Boot\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Learn Spring Boot"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void createTaskWithBlankTitleReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation failed"))
                .andExpect(jsonPath("$.message").value("Title must not be blank"));
    }

    @Test
    void getTaskWithNonexistentIdReturnsNotFound() throws Exception {
        mockMvc.perform(get("/tasks/9999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTaskChangesTitleAndCompletionStatus() throws Exception {
        MvcResult createResult = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Learn Java\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String response = createResult.getResponse().getContentAsString();

        String idString = response.replaceAll(".*\"id\":(\\d+).*", "$1");
        long id = Long.parseLong(idString);

        mockMvc.perform(put("/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Learn Spring Boot\",\"completed\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Learn Spring Boot"))
                .andExpect(jsonPath("$.completed").value(true));
    }
}
