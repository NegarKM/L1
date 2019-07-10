package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IPostRepository;
import com.loyaltyone.interview.repository.IUserRepository;
import com.loyaltyone.interview.web.bean.PostVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowPostsTests {
    @Mock
    IPostRepository postRepoMock;

    @Mock
    IUserRepository userRepoMock;

    @InjectMocks
    PostsController postsController;

    String text;
    String username;
    String invalidUsername;
    User user;
    Post post;
    List<Post> posts;

    @Before
    public void setup() {
        text = "What is the purpose of this test?";

        username = "negar";
        invalidUsername = "invalidUsername";

        user = new User();
        user.setUsername(username);

        post = new Post();
        post.setText(text);
        post.setTimestamp(new Timestamp(System.currentTimeMillis()));
        post.setUser(user);
        posts = new ArrayList<>();
        posts.add(post);
    }

    @Test
    public void showPostsTest_Successful() {
        when(userRepoMock.findByUsername(username))
                .thenReturn(user);

        when(postRepoMock.fetchPostsByUser(user.getId()))
                .thenReturn(new ArrayList<>());

        ResponseEntity<List<PostVO>> postVOs = postsController.getAllPosts(username);

        assertEquals(0, Objects.requireNonNull(postVOs.getBody()).size());
    }

    @Test
    public void showPostsTest_Successful2() {
        when(userRepoMock.findByUsername(username))
                .thenReturn(user);

        when(postRepoMock.fetchPostsByUser(any()))
                .thenReturn(posts);

        ResponseEntity<List<PostVO>> postVOs = postsController.getAllPosts(username);

        assertEquals(HttpStatus.OK, postVOs.getStatusCode());
        assertEquals(1, Objects.requireNonNull(postVOs.getBody()).size());
    }

    @Test
    public void showPostsTest_MissingUsername() {
        ResponseEntity<List<PostVO>> postVOs = postsController.getAllPosts(null);

        assertNull(postVOs.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, postVOs.getStatusCode());
    }

    @Test
    public void showPostsTest_InvalidUsername() {
        when(userRepoMock.findByUsername(invalidUsername))
                .thenReturn(null);

        ResponseEntity<List<PostVO>> postVOs = postsController.getAllPosts(invalidUsername);

        assertNull(postVOs.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, postVOs.getStatusCode());
    }
}
