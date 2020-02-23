package com.app.todo.repository;

import com.app.todo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTypeAndUsername(String type, String username);
    Optional<User> findByTypeAndExternalId(String type, String externalId);

}
