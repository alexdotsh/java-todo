package com.app.todo.repository;

import com.app.todo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")

public interface UserRepository extends CrudRepository<User, Long> {
}
