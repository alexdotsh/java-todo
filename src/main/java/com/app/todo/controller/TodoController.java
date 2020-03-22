package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.services.TodoService;
import com.app.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TodoController {
    private TodoRepository todoRepository;
    private TodoService todoService;

    @Autowired
    public TodoController(TodoRepository todoRepository, TodoService todoService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }

    @GetMapping({"/", "/todos"})
    public String index(Model model) {
        Iterable<Todo> todos = todoRepository.findAll();

        model.addAttribute("todos", todos);

        return "todo/index";
    }

    @GetMapping("todos/create")
    public String create(Model model) {
        model.addAttribute("todo", new Todo());

        return "todo/new";
    }

    @PostMapping("todos/create")
    public String create(@Valid @ModelAttribute Todo todo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "todo/new";
        }

        todoService.save(todo);

        return "redirect:/";
    }

    @GetMapping("todos/{Id}/update")
    public String update(@PathVariable Long Id, Model model) {
        Todo todo = todoRepository.findById(Id).get();
        model.addAttribute("todo", todo);

        return "todo/new";
    }

    @GetMapping("todos/{Id}/delete")
    public String delete(@PathVariable Long Id) {
        todoService.delete(Id);

        return "redirect:/";
    }
}
