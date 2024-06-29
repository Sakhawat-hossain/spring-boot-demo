package com.example.demo.controller;

import com.example.demo.user.RegisterRequest;
import com.example.demo.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MVCController {
    private static final Logger logger = LoggerFactory.getLogger(MVCController.class);

    UserService userService;

    @Autowired
    public MVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("brandName", "Demo Application");

        logger.debug("Login page return");
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/register")
    public String registerPage () {
        logger.debug("Register page return");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser (@RequestParam String firstName, @RequestParam String lastName,
        @RequestParam String email, @RequestParam String password) {
        logger.debug("FirstName=" + firstName + ", LastName=" + lastName + ", Email=" + email
            + ", Password=" + password);

        RegisterRequest request = new RegisterRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setPassword(password);

        userService.registerUser(request);

        return "login";
    }
}
