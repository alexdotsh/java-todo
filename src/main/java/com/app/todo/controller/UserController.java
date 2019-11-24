package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository user_repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        Iterable<User> users = user_repository.findAll();

//        ArrayList<User> users = new ArrayList<User>(); //
//        User user = new User();
//        user.setLogin("login1");
//        Set<Todo> todos = new HashSet<Todo>();
//        Todo todo = new Todo();
//        todo.setTitle("todo title");
//        todos.add(todo);
//        user.setTodos(todos);
//        users.add(user);

        model.addAttribute("users", users);

        return "user/index";
    }
}
