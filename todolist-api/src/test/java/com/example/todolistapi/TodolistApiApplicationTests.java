package com.example.todolistapi;

import com.example.todolistapi.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.todolistapi.exception.ResourceNotFoundException;
import com.example.todolistapi.model.Todo;
import com.example.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodolistApiApplicationTests {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        List<Todo> todos = Arrays.asList(
                newTodo(1L, "Task 1", "Description 1"),
                newTodo(2L, "Task 2", "Description 2")
        );
        when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();
        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    public void testGetTodoById_WhenExists() {
        Todo todo = newTodo(1L, "Task", "Description");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Todo result = todoService.getTodoById(1L);
        assertEquals("Task", result.getTitle());
        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTodoById_WhenNotExists() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> todoService.getTodoById(1L));
    }

    @Test
    public void testCreateTodo() {
        Todo todo = newTodo(null, "Task", "Description");
        Todo saved = newTodo(1L, "Task", "Description");
        when(todoRepository.save(todo)).thenReturn(saved);

        Todo result = todoService.createTodo(todo);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateTodo() {
        Todo existing = newTodo(1L, "Old Title", "Old Description");
        Todo updated = newTodo(1L, "New Title", "New Description");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(todoRepository.save(existing)).thenReturn(updated);

        Todo result = todoService.updateTodo(1L, updated);
        assertEquals("New Title", result.getTitle());
    }

    @Test
    public void testDeleteTodo() {
        Todo todo = newTodo(1L, "Task", "Description");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        doNothing().when(todoRepository).delete(todo);

        todoService.deleteTodo(1L);
        verify(todoRepository, times(1)).delete(todo);
    }

    private Todo newTodo(Long id, String title, String description) {
        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setDescription(description);
        return todo;
    }

}
