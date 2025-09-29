package com.taskmanager.repository;

import com.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Task entity
 * Provides CRUD operations for tasks in Redis
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, String> {
    
    /**
     * Find all tasks belonging to a specific user
     * @param userId the user ID to search for
     * @return List of tasks belonging to the user
     */
    List<Task> findByUserId(String userId);
    
    /**
     * Find a specific task by ID and user ID
     * @param id the task ID
     * @param userId the user ID
     * @return Optional containing the task if found and belongs to user
     */
    Optional<Task> findByIdAndUserId(String id, String userId);
}

