package com.example.tasktracker.repository;

import com.example.tasktracker.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    // Get all completed tasks
    List<Task> findByCompletedTrue();

    // Get all pending tasks
    List<Task> findByCompletedFalse();

    // Get tasks created after a certain date
    List<Task> findByCreatedAtAfter(LocalDateTime date);
}
