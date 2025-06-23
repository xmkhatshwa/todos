package com.digiexperia.springboot.todos.service;

import com.digiexperia.springboot.todos.request.TodoRequest;
import com.digiexperia.springboot.todos.response.TodoResponse;

import java.util.List;

public interface TodoService {
    List<TodoResponse> getAllTodos();
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse updateTodo(long id, TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}