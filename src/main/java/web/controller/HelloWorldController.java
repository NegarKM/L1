package web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import web.bean.Greetings;

@RestController
public class HelloWorldController {
    @GetMapping(path = "hello-world")
    @ResponseBody
    public Greetings sayHello() {
        return new Greetings("Hello World!");
    }
}
