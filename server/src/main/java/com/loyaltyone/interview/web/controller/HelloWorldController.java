package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.Greetings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    @GetMapping("/hello-world")
    public ResponseEntity<Greetings> sayHello() {
        return new ResponseEntity<>(new Greetings("Hello World!"), HttpStatus.OK);
    }
}
