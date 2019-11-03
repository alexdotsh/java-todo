package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {

    @Autowired
    TodoRepository er;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        Iterable<Todo> todos = er.findAll();

        model.addAttribute("todos", todos);

        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTodo(Model model) {

        return "new";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String createTodo(Todo todo, Model model) {
        er.save(todo);

        Iterable<Todo> todos = er.findAll();
        model.addAttribute("todos", todos);

        return "index";
    }

}
