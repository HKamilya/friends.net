package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.services.UsersService;

@RequestMapping("/confirm/")
@RestController
public class ConfirmController {
    @Autowired
    private UsersService usersService;

    @GetMapping("{confirm_code}")
    public ModelAndView getConfirmation(@PathVariable("confirm_code") String confirm_code) {
        boolean isUpdated = usersService.checkState(confirm_code);
        ModelAndView modelAndView = new ModelAndView();
        if (isUpdated) {
            modelAndView.setViewName("success_signup");
        } else {
            modelAndView.setViewName("error_page");
        }

        return modelAndView;
    }
}
