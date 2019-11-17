package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.repository.IUserRepository;
import com.loyaltyone.interview.web.bean.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class EchoController {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPostRepository postRepo;

    @GetMapping("/echo") // it's actually the api for: /createPost
    public ResponseEntity<PostVO> echo(@RequestParam(name="text") String text, @RequestParam(name="user") String username) {
        if (text == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (username == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (text.trim().equals("")) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (text.length() > 256) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRepo.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Post post = postRepo.save(new Post(text, new Timestamp(System.currentTimeMillis()), user));
            return new ResponseEntity<>(new PostVO(post.getText()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
