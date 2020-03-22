package com.app.todo.services;

import com.app.todo.model.User;
import com.app.todo.model.Role;
import com.app.todo.repository.UserRepository;
import com.app.todo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImp implements UserService {
    BCryptPasswordEncoder encoder;
    RoleRepository roleRepository;
    UserRepository userRepository;

    @Autowired
    public UserServiceImp(BCryptPasswordEncoder encoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
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
