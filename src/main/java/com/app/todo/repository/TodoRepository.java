package com.app.todo.repository;

import com.app.todo.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("TodoRepository")
public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
