package com.app.todo.controller;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MyController {

    @RequestMapping(path= "/",method= RequestMethod.GET)
    public ResponseEntity index() {
        System.out.println("controller");


        //var now = LocalDateTime.now();
        //var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //var date_time = dtf.format(now);

        //var params = new HashMap<String, Object>();
        //params.put("date_time", date_time);
        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
        return new ResponseEntity("<response body>",  new HttpHeaders(), HttpStatus.OK);
        //return new ModelAndView("showMessage");

        //return "custom_index.html";

    }

    @RequestMapping(path="/aaa/{id}",method= RequestMethod.GET)
    public String show(@PathVariable String id, Model model) {
        System.out.println("controller::show"+"dsd");//+id.toString());
        model.addAttribute("name", id);
        //return new ResponseEntity("controller::show"+id,  new HttpHeaders(), HttpStatus.OK);
        return "show";


    }



}