package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.repository.IUserRepository;
import com.loyaltyone.interview.web.bean.PostVO;
import com.loyaltyone.interview.web.models.AddCommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPostRepository postRepo;

    @GetMapping("/getPostsByUser")
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

    @PostMapping("/addComment")
    public ResponseEntity<PostVO> addComment(@RequestBody AddCommentParams addCommentParams) {
        if (addCommentParams.getCommentText() == null || addCommentParams.getParentPostId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Post> parentPost = postRepo.findById(addCommentParams.getParentPostId());
            if (!parentPost.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Post newPost = postRepo.save(new Post(addCommentParams.getCommentText(),
                    new Timestamp(System.currentTimeMillis()), parentPost.get().getUser(), parentPost.get()));

            PostVO postVO = new PostVO(newPost.getText(), newPost.getTimestamp(), newPost.getUser().getUsername());
            return new ResponseEntity<>(postVO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPostsByParentPostId")
    public ResponseEntity<?> getPostsByParentPostId(@RequestParam(name="parentPostId") Long parentPostID) {
        if (parentPostID == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            List<Post> childPosts = postRepo.fetchPostsByParentPostID(parentPostID);

            List<PostVO> resultPosts = new ArrayList<>();
            for (Post userPost : childPosts) {
                resultPosts.add(new PostVO(userPost.getText(), userPost.getTimestamp(), userPost.getUser().getUsername()));
            }
            return new ResponseEntity<>(resultPosts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
