package com.app.todo.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.todo.model.User;
import com.app.todo.model.Role;
import com.app.todo.model.Todo;
import com.app.todo.repository.UserRepository;
import com.app.todo.repository.RoleRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        // if (user.getTodos() != null) {
        //     user.getTodos().clear();
        //     user.getTodos().addAll(user.getTodos());
        // }

        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
