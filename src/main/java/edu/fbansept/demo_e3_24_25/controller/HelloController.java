package edu.fbansept.demo_e3_24_25.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping({"/hello","/"})
    public String bonjour() {



        return "<h1>Hello world</h1>";
    }

}
