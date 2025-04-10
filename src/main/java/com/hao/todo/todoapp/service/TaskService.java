package com.hao.todo.todoapp.service;

import com.hao.todo.todoapp.entity.Task;
import com.hao.todo.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return  taskRepository.findAll();
    }

    public Task getTaskByID(int id){
        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found by id:"+id ));
    }

    public Task CreateTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Task.Status.pending);
    return taskRepository.save(task);
    }

    public Task UpdateTask(int id, Task taskDetails){
        Task task = getTaskByID(id);

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void DeleteTask(int id){
        Task task = getTaskByID(id);
        taskRepository.delete(task);
    }

}
