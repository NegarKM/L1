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

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port = 0"})
@ActiveProfiles("test")
public class EchoIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    String testUsername = "test";
    String testPassword = "123";

    @Test
    public void shouldReturn200() {
        String text = "t1 - I enjoy learning new things!";
        String username = "test";
        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/echo?text=" + text + "&user=" + username,
                PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(entity.getBody().getText()).isEqualTo(text);
    }

    @Test
    public void shouldReturn400_missingParameter() {
        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/echo",
                PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturn400_invalidParameter() {
        String text = "t1 - I enjoy learning new things!";
        String username = "test";

        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/echo?abc=" + text + "&user=" + username,
                PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturn400_missingParameterName() {
        ResponseEntity<PostVO> entity = testRestTemplate
                .withBasicAuth(testUsername, testPassword)
                .getForEntity("http://localhost:" + this.port + "/echo?I enjoy learning new things!",
                PostVO.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}