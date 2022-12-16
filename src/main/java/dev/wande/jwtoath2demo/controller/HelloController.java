package dev.wande.jwtoath2demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String hello(Principal principal) {
        return "Hello, " + principal.getName();
    }

}
