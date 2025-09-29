package com.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Task entity for Redis storage
 */
@RedisHash("Task")
public class Task {
    
    @Id
    private String id;
    
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    @Indexed
    private String userId;
    
    // Default constructor
    public Task() {}
    
    // Constructor with parameters
    public Task(String title, String description, TaskStatus status, String userId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                '}';
    }
}
