package sidhardhjoe.java.learnjava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // HTTP Get method
    @GetMapping("/hello-world")
    public String HelloWorld() {
        return "Hello World !";
    }
}
