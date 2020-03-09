package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import com.app.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    Validator validator;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private TodoService todoService;

    @GetMapping({"/", "/todos"})
    public String index(Model model) {
        Iterable<Todo> todos = todoRepository.findAll();

        model.addAttribute("todos", todos);

        return "todo/index";
    }

    @GetMapping("todos/new")
    public String newTodo(Todo todo) { return "todo/new"; }

    @PostMapping("todos/create")
    public String create(@Valid @ModelAttribute Todo todo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            todoService.save(todo);

            return "redirect:/";
        } else {
            return "todo/new";
        }
    }

    @RequestMapping(value = "todos/{Id}/update", method = RequestMethod.GET)
    public String updateTodo(@PathVariable Long Id, Model model) {
        Todo todo = todoRepository.findById(Id).get();
        model.addAttribute("todo", todo);

        return "todo/new";
    }

    @GetMapping("todos/{Id}/delete")
    public String deleteTodo(@PathVariable Long Id) {
        todoService.delete(Id);

        return "redirect:/";
    }
}
