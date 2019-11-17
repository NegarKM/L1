package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.PostVO;
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

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port = 0"})
@ActiveProfiles("test")
public class GetPostsByParentPostIDTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String testUsername = "test";
    private String testPassword = "123";

    @Test
    public void getPostsByParentID_OK() {
        long parentPostId = 5L;

        ResponseEntity<?> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/getPostsByParentPostId?parentPostId=" + parentPostId
                        , Object.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(((List) Objects.requireNonNull(entity.getBody())).size()).isGreaterThan(0);

        System.out.println(Objects.requireNonNull(entity.getBody()).toString());
    }

    @Test
    public void getPostsByParentID_empty_OK() {
        long parentPostId = 0L;

        ResponseEntity<?> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/getPostsByParentPostId?parentPostId=" + parentPostId
                        , Object.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(((List) Objects.requireNonNull(entity.getBody())).size()).isEqualTo(0);
    }

    @Test
    public void getPostsByParentID_BadRequest() {
        ResponseEntity<?> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/getPostsByParentPostId?parentPostId="
                        , Object.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
