package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.repository.IUserRepository;
import com.loyaltyone.interview.web.bean.CityVO;
import com.loyaltyone.interview.web.bean.PostVO;
import com.loyaltyone.interview.web.models.AddCommentParams;
import com.loyaltyone.interview.web.models.CreatePostParams;
import com.mashape.unirest.http.exceptions.UnirestException;
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
                allPosts.add(createPostVO(userPost));
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

            PostVO postVO = createPostVO(newPost);
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
                PostVO postVO = createPostVO(userPost);
                resultPosts.add(postVO);
            }
            return new ResponseEntity<>(resultPosts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createPost")
    public ResponseEntity<PostVO> createPost(@RequestBody CreatePostParams createPostParams) {
        String text = createPostParams.getText();
        String username = createPostParams.getUsername();
        String cityName = createPostParams.getCityName();
        if (text == null || username == null || cityName == null) {
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
            Post post = postRepo.save(new Post(text, new Timestamp(System.currentTimeMillis()), user, cityName));

            PostVO postVO = createPostVO(post);
            return new ResponseEntity<>(postVO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static PostVO createPostVO(Post post) {
        CityVO cityVO = null;
        if (post.getCity() != null) {
            try {
                cityVO = CityController.getCityDetailsFromThirdParty(post.getCity());
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        return new PostVO(post.getId(), post.getText(), post.getTimestamp(), post.getUser().getUsername(), cityVO);
    }
}
