package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.PostVO;
import com.loyaltyone.interview.web.models.AddCommentParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port = 0"})
@ActiveProfiles("test")
public class AddCommentTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String testUsername = "test";
    private String testPassword = "123";

    @Test
    public void addComment_OK() {
        String commentText = "my comment " + Math.random();
        Long parentPostId = 5L;
        AddCommentParams addCommentParams = new AddCommentParams();
        addCommentParams.setCommentText(commentText);
        addCommentParams.setParentPostId(parentPostId);
        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .postForEntity("http://localhost:" + this.port + "/addComment", addCommentParams, PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(Objects.requireNonNull(entity.getBody()).getText()).isEqualTo(commentText);
    }

    @Test
    public void addComment_invalidParentPostID_BadRequest() {
        String commentText = "my comment " + Math.random();
        Long parentPostId = 0L;
        AddCommentParams addCommentParams = new AddCommentParams();
        addCommentParams.setCommentText(commentText);
        addCommentParams.setParentPostId(parentPostId);
        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .postForEntity("http://localhost:" + this.port + "/addComment", addCommentParams, PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
