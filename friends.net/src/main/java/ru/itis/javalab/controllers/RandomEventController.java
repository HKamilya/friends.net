package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.model.Review;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.RequestsService;
import ru.itis.javalab.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class RandomEventController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private RequestsService requestsService;
    @Autowired
    private ReviewsService reviewsService;

    @RequestMapping(value = "/randomEvent", method = RequestMethod.GET)
    public String getRandomEventPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("auth", userDetails.getUsername());
        }
        Event event = eventsService.findRandom();
        Date date = new Date(event.getDate().getTime());
        if (date.after(new Date())) {
            model.addAttribute("time", "");
        }
        model.addAttribute("event", event);
        List<Request> requestList = requestsService.findAllByEventId(event.getId());
        model.addAttribute("numOfReq", requestList.size());
        List<Review> reviewList = reviewsService.findAllByEventId(event.getId());
        model.addAttribute("reviewsList", requestList);
        if (userDetails != null) {
            model.addAttribute("auth", userDetails.getUsername());
        }
        return "event_page";
    }
}
