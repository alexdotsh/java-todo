package com.app.todo.repository;

import com.app.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findByTypeAndExternalId(String type, String externalId);
}
