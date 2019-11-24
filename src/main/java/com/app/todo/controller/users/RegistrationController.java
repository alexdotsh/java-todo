package com.app.todo.controller.users;

import com.app.todo.model.User;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository user_repository;

    @GetMapping("/users/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "user/registration";
    }

    @PostMapping("/users/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            user_repository.save(user);
        } else {
            return "user/registration";
        }
        
        return "redirect:/welcome";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "user/welcome";
    }
}
