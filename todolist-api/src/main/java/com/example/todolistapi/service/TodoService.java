package com.example.todolistapi.service;

import com.example.todolistapi.exception.ResourceNotFoundException;
import com.example.todolistapi.model.Todo;
import com.example.todolistapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Todo getTodoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    }

    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    public Todo updateTodo(Long id, Todo newTodo) {
        Todo todo = getTodoById(id);
        todo.setTitle(newTodo.getTitle());
        todo.setDescription(newTodo.getDescription());
        return repository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        repository.delete(todo);
    }
}
