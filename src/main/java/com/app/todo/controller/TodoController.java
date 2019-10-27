package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TodoController {

    @Autowired
    TodoRepository er;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {



        Todo todo1 = new Todo();
        todo1.setTitle("title1");
        todo1.setDescription("desc1");

        //er.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("title2");
        todo2.setDescription("desc2");

        Iterable<Todo> todos = er.findAll();//{todo1, todo2};

        model.addAttribute("todos", todos);
        //model.addAttribute("todos", todos);

        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult) {

        er.save(todo);

        Iterable<Todo> todos = er.findAll();

        model.addAttribute("todos", todos);

        return "index";
    }
}
