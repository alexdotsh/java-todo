package com.app.todo.services;

import com.app.todo.model.User;

public interface UserService {
    void save(User user);
    void update(Long Id, User user);

    User findByUsername(String username);
    User findByEmail(String email);
}
