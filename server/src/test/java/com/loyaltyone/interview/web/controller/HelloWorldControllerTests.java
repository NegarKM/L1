package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.web.bean.Greetings;
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
public class HelloWorldControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200() {
        ResponseEntity<Greetings> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/hello-world", Greetings.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(entity.getBody().getMessage()).isEqualTo("Hello World!");
    }
}
