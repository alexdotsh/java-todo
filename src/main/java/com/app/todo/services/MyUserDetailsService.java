package com.app.todo.services;

import com.app.todo.model.LocalPrincipal;
import com.app.todo.model.LocalUser;
import com.app.todo.model.User;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    public MyUserDetailsService(){
        System.out.println("MyUserDetailsService");
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByTypeAndUsername("Local", username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new LocalPrincipal((LocalUser)user.get());
    }
}
