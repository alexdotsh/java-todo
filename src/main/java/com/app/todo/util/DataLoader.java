package com.app.todo.util;

import com.app.todo.model.Role;
import com.app.todo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    @Override
    public void run(ApplicationArguments args) {
        Role role_user = roleRepository.findByRole("ROLE_USER");
        Role role_admin = roleRepository.findByRole("ROLE_ADMIN");

        if (role_user == null) {
            role_user = new Role();
            role_user.setRole("ROLE_USER");
            roleRepository.save(role_user);
        }

        if (role_admin == null) {
            role_admin = new Role();
            role_admin.setRole("ROLE_ADMIN");
            roleRepository.save(role_admin);
        }
    }
}
