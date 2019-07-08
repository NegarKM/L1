package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EchoController {
    @GetMapping("/echo")
    public ResponseEntity<Post> echo(@RequestParam(name="text") String text) {
        if (text == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Post(text), HttpStatus.OK);
    }
}
