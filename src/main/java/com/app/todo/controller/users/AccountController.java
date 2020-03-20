package com.app.todo.controller;

import com.app.todo.model.User;
import com.app.todo.services.UserService;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("user/account")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = ((AuthenticatedPrincipal)principal).getName();
        }

        User user = userRepository.findByUsername(username);

        model.addAttribute("user", user);

        return "user/index";
    }

    @GetMapping("user/{Id}/update")
    public String update(@PathVariable Long Id, Model model) {
        User user = userRepository.findById(Id).get();

        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("user/update")
    public String update(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/update";
        }

        userService.update(user);

        return "redirect:account";
    }
}
