package com.app.todo.services;

import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImp implements TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Todo todo) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByUsername(username);
        todo.setTitle(todo.getTitle());
        todo.setDescription(todo.getDescription());
        todo.setUser(user);
        todoRepository.save(todo);
    }
}
