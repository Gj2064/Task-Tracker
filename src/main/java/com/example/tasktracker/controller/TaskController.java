package com.example.tasktracker.controller;

import com.example.tasktracker.models.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import com.example.tasktracker.dto.TaskDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get all completed tasks
    @GetMapping("/completed")
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }

    // Get all pending tasks
    @GetMapping("/pending")
    public List<Task> getPendingTasks() {
        return taskRepository.findByCompletedFalse();
    }
 @GetMapping("/{id}")
public Task getTaskById(@PathVariable String id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
}
// Mark a task as completed
@PutMapping("/{id}/complete")
public Task completeTask(@PathVariable String id) {
    return taskRepository.findById(id).map(task -> {
        task.setCompleted(true); // Set completed to true
        task.setEndedAt(LocalDateTime.now()); // Optional: Set end time when completed
        return taskRepository.save(task);
    }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}



    // Get tasks created in the last 7 days
    @GetMapping("/recent")
    public List<Task> getRecentTasks() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
        return taskRepository.findByCreatedAtAfter(oneWeekAgo);
    }

    // Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // Update a task by ID
@PutMapping("/{id}")
public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
    return taskRepository.findById(id).map(task -> {
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}

// Delete a task by ID
@DeleteMapping("/{id}")
public String deleteTask(@PathVariable String id) {
    if (!taskRepository.existsById(id)) {
        throw new RuntimeException("Task not found with id " + id);
    }
    taskRepository.deleteById(id);
    return "Task deleted successfully!";
}
// Start a task (set startedAt timestamp)
@PutMapping("/{id}/start")
public Task startTask(@PathVariable String id) {
    return taskRepository.findById(id).map(task -> {
        task.setStartedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}

// End a task (set endedAt timestamp)
@PutMapping("/{id}/end")
public Task endTask(@PathVariable String id) {
    return taskRepository.findById(id).map(task -> {
        task.setEndedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}

}
