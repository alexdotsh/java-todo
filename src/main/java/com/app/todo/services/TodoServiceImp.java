package com.app.todo.services;

import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class TodoServiceImp implements TodoService {
    TodoRepository todoRepository;
    UserRepository userRepository;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Todo todo) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = ((AuthenticatedPrincipal)principal).getName();
        }

        User user = userRepository.findByUsername(username);
        todo.setTitle(todo.getTitle());
        todo.setDescription(todo.getDescription());
        todo.setUser(user);
        todoRepository.save(todo);
    }

    @Override
    public void delete(Long Id) { todoRepository.deleteById(Id); }

    @Override
    public void done(Long Id) {
        Todo todo = todoRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("Todo not found"));
        todo.setDone(true);
        todoRepository.save(todo);
    }
}
