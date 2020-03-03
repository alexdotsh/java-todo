package com.app.todo.controller;

//import com.app.todo.model.MyPrincipal;
import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todo_repository;

    @Autowired
    private UserRepository user_repository;

    @Autowired
    Validator validator;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @RequestMapping(value ={"/todos", "/"}, method = RequestMethod.GET)
    public String index(Model model, Authentication authentication) {

        Iterable<Todo> todos = todo_repository.findAll();
//        MyPrincipal principal = (MyPrincipal)authentication.getPrincipal();

        model.addAttribute("todos", todos);
//        model.addAttribute("principal", principal);

        return "todo/index";
    }

    @RequestMapping(value = "todos/new", method = RequestMethod.GET)
    public String newTodo(Todo todo) {
        return "todo/new";
    }

    @RequestMapping(value = "todos/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        User user = user_repository.findByUsername(username);

        if (!bindingResult.hasErrors()){
            todo.setTitle(todo.getTitle());
            todo.setDescription(todo.getDescription());
            todo.setUser(user);
            todo_repository.save(todo);
            Iterable<Todo> todos = todo_repository.findAll();

            return "redirect:/";
        } else {
            return "todo/new";
        }
    }

    @RequestMapping(value = "todos/{Id}/update", method = RequestMethod.GET)
    public String updateTodo(@PathVariable Long Id, Model model) {
        Optional<Todo> maybe_todo = todo_repository.findById(Id);
        Todo todo = maybe_todo.get();
        model.addAttribute("todo", todo);

        return "todo/new";
    }

    @RequestMapping(value = "todos/{Id}", method = RequestMethod.DELETE)
    public String deleteTodo(@PathVariable Long Id) {

        todo_repository.deleteById(Id);
        return "redirect:/";
    }
}
