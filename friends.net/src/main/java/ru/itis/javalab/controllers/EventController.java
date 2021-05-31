package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.model.Review;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.security.details.UserDetailsServiceImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.RequestsService;
import ru.itis.javalab.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventsService eventsService;


    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String getEventPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "id") Long id, Model model) {
        Optional<Event> event = eventsService.findById(id);
        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                model.addAttribute("time", "");
            }
            model.addAttribute("event", event.get());
            model.addAttribute("reviews", event.get().getReviews());
            model.addAttribute("requests", event.get().getRequests());
            if (userDetails != null) {
                model.addAttribute("auth", userDetails.getUsername());
            }
        }

        return "event_page";
    }
}
