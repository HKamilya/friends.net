package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.model.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {
    @Autowired
    private UsersService usersService;



    @GetMapping(value = "/success")
    public String getSuccessPage() {
        return "success_signup";
    }

    @GetMapping(value = "/error")
    public String getErrorPage() {
        return "error_page";
    }


    @GetMapping(value = "/signUp")
    public String getRegisterPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_view";
    }

    @PostMapping(value = "/signUp")
    public String signUp(@Valid UserForm userForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userForm", userForm);
            return "sign_up_view";
        }
        usersService.save(userForm);
        HttpSession session = request.getSession();
        User user1 = usersService.findByName(userForm.getUsername());
        session.setMaxInactiveInterval(10 * 60);
        session.setAttribute("User", user1);
        return "redirect:/signIn";
    }

}

