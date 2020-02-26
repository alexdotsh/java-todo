package com.app.todo.controller;

import com.app.todo.model.User;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository user_repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        Iterable<User> users = user_repository.findAll();

        model.addAttribute("users", users);

        return "user/index";
    }
}
