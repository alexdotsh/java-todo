package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;

import javax.validation.groups.Default;
import java.util.Optional;

@Controller
//@RequestMapping("todos") unfortunately can't be used as root path is here.
public class TodoController {

    @Autowired
    private TodoRepository todo_repository;
    @Autowired
    private UserRepository user_repository;

    @Autowired
    Validator validator;

    @RequestMapping(value ={"/todos", "/"}, method = RequestMethod.GET)
    public String index(Model model) {

        Iterable<Todo> todos = todo_repository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("todos", todos);
        model.addAttribute("principal", auth.getPrincipal());

        return "todo/index";
    }

    @RequestMapping(value = "todos/new", method = RequestMethod.GET)
    public String newTodo(Todo todo) {
        return "todo/new";
    }

    @RequestMapping(value = "todos/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult, @RequestParam String username) {
        User user;

        Optional<User> searched_user = user_repository.findByUsername(username);
        if(searched_user.isPresent())
            user = searched_user.get();
        else {
            user = new User();
            user.setUsername(username);
            user_repository.save(user);
        }

        if(!bindingResult.hasErrors()){
            todo.setUser(user);
            todo.setDescription(todo.getDescription()+user.getUsername());
            todo_repository.save(todo);
            Iterable<Todo> todos = todo_repository.findAll();

            //model.addAttribute("todos", todos);
            return "redirect:/";
        }else{
            return "todo/new";
        }
    }

    @RequestMapping(value = "todos/{Id}/update", method = RequestMethod.GET)
    public String updateTodo(@PathVariable int Id, Model model) {
        Optional<Todo> maybe_todo = todo_repository.findById(Id);
        Todo todo = maybe_todo.get();
        model.addAttribute("todo", todo);

        return "todo/new";
    }

    @RequestMapping(value = "todos/{Id}", method = RequestMethod.DELETE)
    public String deleteTodo(@PathVariable int Id) {

        todo_repository.deleteById(Id);
        return "redirect:/";
    }


}
