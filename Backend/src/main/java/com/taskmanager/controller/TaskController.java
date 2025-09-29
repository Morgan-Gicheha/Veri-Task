package com.taskmanager.controller;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Task controller
 * Handles CRUD operations for tasks
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Get current authenticated user
     */
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    /**
     * Create a new task
     */
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest request) {
        try {
            User currentUser = getCurrentUser();
            
            Task task = new Task();
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setStatus(request.getStatus() != null ? request.getStatus() : TaskStatus.PENDING);
            task.setUserId(currentUser.getId());
            
            Task savedTask = taskRepository.save(task);
            
            logger.info("Task created successfully: {} for user: {}", savedTask.getId(), currentUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
            
        } catch (Exception e) {
            logger.error("Error creating task: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create task");
        }
    }
    
    /**
     * Get all tasks for the current user
     */
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        try {
            User currentUser = getCurrentUser();
            List<Task> tasks = taskRepository.findByUserId(currentUser.getId());
            
            logger.info("Retrieved {} tasks for user: {}", tasks.size(), currentUser.getUsername());
            return ResponseEntity.ok(tasks);
            
        } catch (Exception e) {
            logger.error("Error retrieving tasks: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve tasks");
        }
    }
    
    /**
     * Get a specific task by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) {
        try {
            User currentUser = getCurrentUser();
            Optional<Task> taskOpt = taskRepository.findById(id);
            
            if (taskOpt.isPresent() && taskOpt.get().getUserId().equals(currentUser.getId())) {
                logger.info("Task retrieved: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.ok(taskOpt.get());
            } else {
                logger.warn("Task not found or access denied: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            logger.error("Error retrieving task: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve task");
        }
    }
    
    /**
     * Update an existing task
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @Valid @RequestBody TaskRequest request) {
        try {
            User currentUser = getCurrentUser();
            Optional<Task> taskOpt = taskRepository.findById(id);
            
            if (taskOpt.isPresent() && taskOpt.get().getUserId().equals(currentUser.getId())) {
                Task task = taskOpt.get();
                task.setTitle(request.getTitle());
                task.setDescription(request.getDescription());
                if (request.getStatus() != null) {
                    task.setStatus(request.getStatus());
                }
                
                Task updatedTask = taskRepository.save(task);
                
                logger.info("Task updated successfully: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.ok(updatedTask);
            } else {
                logger.warn("Task not found or access denied for update: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            logger.error("Error updating task: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update task");
        }
    }
    
    /**
     * Delete a task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        try {
            User currentUser = getCurrentUser();
            Optional<Task> taskOpt = taskRepository.findById(id);
            
            if (taskOpt.isPresent() && taskOpt.get().getUserId().equals(currentUser.getId())) {
                taskRepository.delete(taskOpt.get());
                
                logger.info("Task deleted successfully: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.ok().build();
            } else {
                logger.warn("Task not found or access denied for deletion: {} for user: {}", id, currentUser.getUsername());
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            logger.error("Error deleting task: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete task");
        }
    }
}
