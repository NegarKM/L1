package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.repository.IUserRepository;
import com.loyaltyone.interview.web.bean.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPostRepository postRepo;

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostVO>> getAllPosts(@RequestParam(name="user") String username) {
        if (username == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRepo.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<Post> userPosts = postRepo.fetchPostsByUser(user.getId());
            List<PostVO> allPosts = new ArrayList<>();
            for (Post userPost : userPosts) {
                allPosts.add(new PostVO(userPost.getText(), userPost.getTimestamp(), userPost.getUser().getUsername()));
            }
            return new ResponseEntity<>(allPosts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
