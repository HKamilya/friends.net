package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {


    @GetMapping(value = "/")
    public String getHomePage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "main_view";
    }
}
