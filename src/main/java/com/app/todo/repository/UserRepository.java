package com.app.todo.repository;

import com.app.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
