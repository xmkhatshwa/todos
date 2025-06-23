package com.digiexperia.springboot.todos.service;

import com.digiexperia.springboot.todos.entity.Todo;
import com.digiexperia.springboot.todos.entity.User;
import com.digiexperia.springboot.todos.repository.TodoRepository;
import com.digiexperia.springboot.todos.request.TodoRequest;
import com.digiexperia.springboot.todos.response.TodoResponse;
import com.digiexperia.springboot.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        return todoRepository.findByOwner(currentUser)
                .stream()
                .map(this::convertToTodoResponse)
                .toList();
    }

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo todo = getTodo(todoRequest);

        Todo savedTodo = todoRepository.save(todo);

        return convertToTodoResponse(savedTodo);
    }

    @Override
    public TodoResponse updateTodo(long id, TodoRequest todoRequest) {

        Todo todo = getTodo(todoRequest);

        todo.setId(id);
        Todo savedTodo = todoRepository.save(todo);

        return convertToTodoResponse(savedTodo);
    }

    private Todo getTodo(TodoRequest todoRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        return new Todo(
                todoRequest.getTitle(),
                todoRequest.getDescription(),
                todoRequest.getPriority(),
                false,
                currentUser
        );
    }

    @Override
    @Transactional
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if (todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }

        todo.get().setComplete(!todo.get().isComplete());
        Todo updatedTodo = todoRepository.save(todo.get());

        return convertToTodoResponse(updatedTodo);
    }

    @Transactional
    public void deleteTodo(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if (todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        todoRepository.delete(todo.get());
    }

    private TodoResponse convertToTodoResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getPriority(),
                todo.isComplete()
        );
    }
}







