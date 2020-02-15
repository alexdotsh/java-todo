package com.app.todo.controller;

import com.app.todo.model.MyPrincipal;
import com.app.todo.model.Todo;
import com.app.todo.model.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.validation.Valid;

import javax.validation.groups.Default;
import java.util.Map;
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

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

//    @GetMapping("/loginSuccess")
//    public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(
//                        authentication.getAuthorizedClientRegistrationId(),
//                        authentication.getName());
//
//        String userInfoEndpointUri = client.getClientRegistration()
//                .getProviderDetails().getUserInfoEndpoint().getUri();
//
//        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
//                    .getTokenValue());
//            HttpEntity entity = new HttpEntity("", headers);
//            ResponseEntity<Map> response = restTemplate
//                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
//            Map userAttributes = response.getBody();
//            model.addAttribute("name", userAttributes.get("name"));
//        }
//
//        int aaa = 4;
//        return "loginSuccess";
//    }

    @RequestMapping(value ={"/todos", "/"}, method = RequestMethod.GET)
    public String index(Model model) {

        Iterable<Todo> todos = todo_repository.findAll();
        MyPrincipal principal = (MyPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("todos", todos);
        model.addAttribute("principal", principal);

        return "todo/index";
    }

    @RequestMapping(value = "todos/new", method = RequestMethod.GET)
    public String newTodo(Todo todo) {
        return "todo/new";
    }

    @RequestMapping(value = "todos/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult, @RequestParam String username) {
        User user;

        Optional<User> searched_user = user_repository.findByUsername("test_login");
        if(searched_user.isPresent())
            user = searched_user.get();
        else {
            user = new User();
            user.setUsername("test_login");
            user_repository.save(user);
        }

        if(!bindingResult.hasErrors()){
            todo.setUser(user);
            todo.setDescription(todo.getDescription());
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
