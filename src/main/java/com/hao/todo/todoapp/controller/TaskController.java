package com.hao.todo.todoapp.controller;

import com.hao.todo.todoapp.entity.Task;
import com.hao.todo.todoapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.CreateTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.UpdateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.DeleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
