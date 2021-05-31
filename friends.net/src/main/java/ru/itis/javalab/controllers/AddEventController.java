package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.model.Category;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.CategoriesService;
import ru.itis.javalab.services.EventsService;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@MultipartConfig
@Controller
public class AddEventController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private CategoriesService categoriesService;


    @PostMapping(value = "/addEvent")
    public String addEvent(EventDto eventDto, @RequestParam("category") Long id, @RequestParam("image") MultipartFile image,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        eventDto.setCategory_id(id);
        eventDto.setFilePart(image);
        eventsService.save(eventDto, userDetails.getId());

        return "redirect:/addEvent";
    }

    @GetMapping(value = "/addEvent")
    public String getAddEventPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        List<Category> categoryList = categoriesService.findAll();
        model.addAttribute("categoryList", categoryList);
        if (userDetails != null) {
            model.addAttribute("auth", userDetails.getUsername());
        }

        return "addEvent_view";
    }
}
