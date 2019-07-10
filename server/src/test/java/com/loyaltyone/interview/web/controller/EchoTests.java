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
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EchoTests {
    @Mock
    IPostRepository postRepoMock;

    @Mock
    IUserRepository userRepoMock;

    @InjectMocks
    EchoController echoController;

    String text;
    String emptyText;
    String tooLongText;
    String username;
    String invalidUsername;
    User user;
    Post post;

    @Before
    public void setup() {
        text = "What is the purpose of this test?";
        emptyText = " ";
        tooLongText = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

        username = "negar";
        invalidUsername = "invalidUsername";

        user = new User();
        user.setUsername(username);

        post = new Post();
        post.setText(text);
        post.setTimestamp(new Timestamp(System.currentTimeMillis()));
        post.setUser(user);
    }

    @Test
    public void echoTest_Successful() {
        when(userRepoMock.findByUsername(username))
                .thenReturn(user);

        when(postRepoMock.save(notNull()))
                .thenReturn(post);

        ResponseEntity<PostVO> postVO = echoController.echo(text, username);

        assertEquals(text, Objects.requireNonNull(postVO.getBody()).getText());
    }

    @Test
    public void echoTest_MissingUsername() {
        ResponseEntity<PostVO> postVO = echoController.echo(text, null);

        assertNull(postVO.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, Objects.requireNonNull(postVO).getStatusCode());
    }

    @Test
    public void echoTest_MissingText() {
        ResponseEntity<PostVO> postVO = echoController.echo(null, username);

        assertNull(postVO.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, postVO.getStatusCode());
    }

    @Test
    public void echoTest_InvalidUsername() {
        when(userRepoMock.findByUsername(invalidUsername))
                .thenReturn(null);

        ResponseEntity<PostVO> postVO = echoController.echo(text, invalidUsername);

        assertNull(postVO.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, postVO.getStatusCode());
    }

    @Test
    public void echoTest_EmptyText() {
        ResponseEntity<PostVO> postVO = echoController.echo(emptyText.trim(), username);

        assertNull(postVO.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, postVO.getStatusCode());
    }

    @Test
    public void echoTest_TooLongText() {
        ResponseEntity<PostVO> postVO = echoController.echo(tooLongText, username);

        assertNull(postVO.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, postVO.getStatusCode());
    }
}

