package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//TODO

@Controller
public class AnProfileController {
    @Autowired
    private UsersService usersService;


    @GetMapping(value = "/anProfile")
    public String getAnProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "username") String username, Model model) {
        User user = usersService.findByName(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("image", user.getImage());
        model.addAttribute("description", user.getDescription());
        if (userDetails != null) {
            model.addAttribute("auth", userDetails.getUsername());
        }
        return "user_view";

    }
}
