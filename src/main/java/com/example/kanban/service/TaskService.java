package com.example.kanban.service;

import com.example.kanban.model.Task;
import com.example.kanban.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> listarTodas() {
        return taskRepository.findAll();
    }

    public Optional<Task> buscarPorId(Long id) {
        return taskRepository.findById(id);
    }

    public Task criar(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> atualizar(Long id, Task taskAtualizada) {
        return taskRepository.findById(id).map(task -> {
            task.setTitulo(taskAtualizada.getTitulo());
            task.setDescricao(taskAtualizada.getDescricao());
            task.setStatus(taskAtualizada.getStatus());
            return taskRepository.save(task);
        });
    }

    public boolean deletar(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
