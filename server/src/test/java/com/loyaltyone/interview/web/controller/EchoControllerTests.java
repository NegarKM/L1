package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port = 0"})
public class EchoControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200() {
        ResponseEntity<Post> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/echo?text=I enjoy learning new things!",
                Post.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(entity.getBody().getText()).isEqualTo("I enjoy learning new things!");
    }

    @Test
    public void shouldReturn400_missingParameter() {
        ResponseEntity<Post> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/echo",
                Post.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturn400_invalidParameter() {
        ResponseEntity<Post> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/echo?abc=I enjoy learning new things!",
                Post.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturn400_missingParameterName() {
        ResponseEntity<Post> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/echo?I enjoy learning new things!",
                Post.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
