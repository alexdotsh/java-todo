package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public String newTodo(Todo todo) {

        return "new";
    }

    @RequestMapping(value = "/todo/{Id}/update", method = RequestMethod.GET)
    public String updateTodo(@PathVariable int Id, Model model) {
        Optional<Todo> maybe_todo = er.findById(Id);
        Todo todo = maybe_todo.get();
        model.addAttribute("todo", todo);

        return "new";
    }

    @RequestMapping(value = "/todo/{Id}", method = RequestMethod.DELETE)
    public String deleteTodo(@PathVariable int Id) {

        er.deleteById(Id);
        return "redirect:/";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()){
            er.save(todo);
            Iterable<Todo> todos = er.findAll();

            model.addAttribute("todos", todos);
            return "index";
        }else{
            return "new";
        }
    }
}
