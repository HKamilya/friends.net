package ru.itis.javalab.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO

@Controller
public class AllEventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping(value = "/allEvents")
    public String getAllEventsPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        List<Event> eventList = eventsService.findAllByStatus();
        List<Event> events = new ArrayList<>();

        for (Event event : eventList) {
            if (event.getDate().after(new Date())) {
                events.add(event);
            }
        }

        if (userDetails != null) {
            model.addAttribute("auth", userDetails.getUsername());
            model.addAttribute("user", userDetails);
        }

        model.addAttribute("list", events);
        return "allEvents_view";
    }

//    @PostMapping(value = "/AllEvents")
//    public void getPrevOrNextPage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "title") String title) {
//        HttpSession session = request.getSession();
//        int page = (int) session.getAttribute("page");
//        if (title.equals("prev")) {
//            page--;
//        }
//        if (title.equals("next")) {
//            page++;
//        }
//        List<Event> eventList = eventsService.findByPage(page, 5);
//        List<Event> events = new ArrayList<>();
//        Date dateNow = new Date();
//        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
//
//        String date = formatForDateNow.format(dateNow);
//        session.setAttribute("page", page);
//        for (Event event : eventList) {
//            if (event.getDate().compareTo(date) >= 0) {
//                events.add(event);
//            }
//        }
//        response.setContentType("application/json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        response.setCharacterEncoding("UTF-8");
//        try {
//            String json = objectMapper.writeValueAsString(events);
//            response.getWriter().write(json);
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
}
