package com.example.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.tasktracker")
public class TasktrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasktrackerApplication.class, args);
    }
}
