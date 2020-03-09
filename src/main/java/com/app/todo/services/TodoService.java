package com.app.todo.services;

import com.app.todo.model.Todo;

public interface TodoService {
    void save(Todo todo);
    void delete(Long Id);
}
