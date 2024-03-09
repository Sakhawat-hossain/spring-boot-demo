package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MVCController {
    @GetMapping("/login")
    public String login(Model model) {
        // ModelAndView mav = new ModelAndView("login");
        // mav.addObject("name", "Demo Application");

        model.addAttribute("pname", "Demo Application");

        return "login";
    }
}
