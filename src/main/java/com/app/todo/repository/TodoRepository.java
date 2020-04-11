package com.app.todo.repository;

import com.app.todo.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t WHERE t.Done = ?1")
    Iterable<Todo> findAllByDone(Boolean done);
}
