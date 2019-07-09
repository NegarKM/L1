package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.web.bean.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
public class EchoController {
    @Autowired
    private IPostRepository repo;

    @GetMapping("/echo")
    public ResponseEntity<PostVO> echo(@RequestParam(name="text") String text) {
        if (text == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            Post post = repo.save(new Post(text, new Timestamp(System.currentTimeMillis())));
            return new ResponseEntity<>(new PostVO(post.getText()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
