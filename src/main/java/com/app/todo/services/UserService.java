package com.app.todo.services;

import com.app.todo.model.User;

public interface UserService {
    void save(User user);
    void update(User user);
}
