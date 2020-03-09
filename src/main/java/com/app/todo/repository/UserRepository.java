package com.app.todo.repository;

import com.app.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByTypeAndUsername(String type, String username);
    Optional<User> findByTypeAndExternalId(String type, String externalId);
}
