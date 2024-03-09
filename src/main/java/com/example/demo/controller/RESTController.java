package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    private static final Logger logger = LoggerFactory.getLogger(RESTController.class);

    @GetMapping({"/", "/home"})
    public String index() {
        logger.debug("Inside index method");
        return "Welcome to Spring Boot Demo project";
    }
}
