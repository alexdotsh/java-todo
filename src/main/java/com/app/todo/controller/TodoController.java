package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {

    //@Autowired
    //TodoRepository er;

    @RequestMapping(value = "/")
    public String index(Model model) {

        Todo todo1 = new Todo();
        todo1.setTitle("title1");
        todo1.setDescription("desc1");

        Todo todo2 = new Todo();
        todo2.setTitle("title2");
        todo2.setDescription("desc2");

        Todo[] todos = {todo1, todo2};

        //model.addAttribute("todos", er.findTotalByAccountType("aa"));
        model.addAttribute("todos", todos);

        return "index";
    }
}
