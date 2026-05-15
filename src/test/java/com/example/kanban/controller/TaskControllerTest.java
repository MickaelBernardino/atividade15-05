package com.example.kanban.controller;

import com.example.kanban.model.Task;
import com.example.kanban.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void deveRetornarListaDeTasksVazia() throws Exception {
        when(taskService.listarTodas()).thenReturn(List.of());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void deveCriarUmaTask() throws Exception {
        Task task = new Task("Implementar CI", "Configurar GitHub Actions", "A_FAZER");
        task.setId(1L);

        when(taskService.criar(any(Task.class))).thenReturn(task);

        String json = """
                {
                    "titulo": "Implementar CI",
                    "descricao": "Configurar GitHub Actions",
                    "status": "A_FAZER"
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Implementar CI"))
                .andExpect(jsonPath("$.status").value("A_FAZER"));
    }

    @Test
    void deveRetornar404QuandoTaskNaoEncontrada() throws Exception {
        when(taskService.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarTaskPorId() throws Exception {
        Task task = new Task("Deploy automático", "Configurar CD", "EM_PROGRESSO");
        task.setId(1L);

        when(taskService.buscarPorId(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Deploy automático"));
    }
}
