package com.app.todo.repository;

import com.app.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
//    List<Todo> findAll();
}
