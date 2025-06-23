package com.digiexperia.springboot.todos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequest {

    @NotEmpty(message = "Title is mandatory")
    @Size(min = 3, max = 30, message = "Title must be at least 3 characters long")
    private String title;

    @NotEmpty(message = "Description is mandatory")
    @Size(min = 3, max = 30, message = "Description must be at least 3 characters long")
    private String description;

    @Min(1)
    @Max(5)
    private int priority;

    public TodoRequest(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
}

