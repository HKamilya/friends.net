package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.ImagesService;
import ru.itis.javalab.services.RequestsService;
import ru.itis.javalab.services.UsersService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private RequestsService requestsService;
    @Autowired
    private UsersService usersService;


    @GetMapping(value = "/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        Optional<User> userOptional = usersService.findById(userDetails.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            model.addAttribute("user", user);

            Optional<Image> image = imagesService.findById(user.getImage().getId());
            List<Event> events = new ArrayList<>();
            for (Event event : user.getEvents()) {
                if (event.getDate().compareTo(new Date()) >= 0) {
                    events.add(event);
                }
            }

            model.addAttribute("auth", userDetails.getUsername());

            model.addAttribute("username", user.getUsername());
            model.addAttribute("description", user.getDescription());
            model.addAttribute("list", events);
            model.addAttribute("image", image.get());
            System.out.println("hi");
        }

        return "user_view";
    }

    @PostMapping(value = "/profile")
    public String postDataOnProfilePage(@RequestParam(value = "event_id") Long event_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestsService.delete(event_id, userDetails.getId());

        return "redirect:/profile";
    }
}
