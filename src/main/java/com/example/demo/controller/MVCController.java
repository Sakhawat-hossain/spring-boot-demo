package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MVCController {
    private static final Logger logger = LoggerFactory.getLogger(MVCController.class);

    @GetMapping("/login")
    public String login(Model model) {
        // ModelAndView mav = new ModelAndView("login");
        // mav.addObject("name", "Demo Application");

        model.addAttribute("pname", "Demo Application");

        logger.debug("Login page return");
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/signup")
    public String signupPage () {
        logger.debug("Signup page return");
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser (@RequestParam String firstName, @RequestParam String lastName,
        @RequestParam String username, @RequestParam String password) {
        logger.debug("FirstName=" + firstName + ", LastName=" + lastName + ", Username=" + username
            + ", Password=" + password);
        return "hello";
    }
}
