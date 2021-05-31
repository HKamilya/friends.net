package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.RequestsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class UserEventsController {

    @Autowired
    private EventsService eventsService;
    @Autowired
    private RequestsService requestsService;

    @RequestMapping(value = "/userEvents", method = RequestMethod.GET)
    public String getUserEvents(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {


        List<Event> eventList = eventsService.findByUserId(userDetails.getId());
        LinkedHashMap<Event, List<Request>> evReqList = new LinkedHashMap<>();
        for (Event event : eventList) {
            List<Request> requests = requestsService.findAllByEventId(event.getId());
            evReqList.put(event, requests);
        }
        model.addAttribute("user", userDetails.getUserDto());
        model.addAttribute("evReqList", evReqList);
        return "userEvents_view";
    }

    @RequestMapping(value = "/userEvents", method = RequestMethod.POST)
    public ModelAndView postUserEvents(HttpServletRequest request) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("UTF-8");
        String status = "";
        int status_id = Integer.parseInt(request.getParameter("status"));
        if (status_id == 1) {
            status = "актуально";
        } else {
            status = "неактуально";
        }
        Long event_id = Long.valueOf(request.getParameter("event_id"));

        Event event = new Event();
        event.setId(event_id);
        event.setStatus(status);
        eventsService.update(event);
        return new ModelAndView("redirect:/userEvents");
    }
}
