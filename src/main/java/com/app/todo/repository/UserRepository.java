package com.app.todo.repository;

import com.app.todo.model.Todo;
import com.app.todo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String Login);
}
