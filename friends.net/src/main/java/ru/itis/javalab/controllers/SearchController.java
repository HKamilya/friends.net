package ru.itis.javalab.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.itis.javalab.model.Category;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.User;
import ru.itis.javalab.services.CategoriesService;
import ru.itis.javalab.services.EventsService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = "/search")
    public String getSearchPage(HttpServletRequest request, Model model) throws JsonProcessingException {
        List<Category> categories = categoriesService.findAll();
        List<String> eventsNames = new ArrayList<>();
        List<Event> eventList = eventsService.findAll();
        for (Event ev : eventList) {
            eventsNames.add(ev.getName());
        }
        eventList.sort((o1, o2) -> (o2.getDate().compareTo(o1.getDate())));

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writeValueAsString(eventsNames);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        model.addAttribute("user", user);
        model.addAttribute("names", json);
        model.addAttribute("catList", categories);
        model.addAttribute("list", eventList);
        return "search_page";
    }

    @PostMapping(value = "/search")
    public void getSearchEvent(@RequestParam(value = "search") String name,
                               @RequestParam(value = "tags") String s,
                               @RequestParam(value = "date") String date,
                               HttpServletResponse response, HttpServletRequest request) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] s1 = s.split(",");
        System.out.println(Arrays.toString(s1));
        List<Long> tags = new ArrayList<>();
        for (String value : s1) {
            Long id = Long.valueOf(value);
            System.out.println(id);
            tags.add(id);
        }
        List<Event> events = eventsService.search(name, tags, date);
        if (events.size() == 0) {
            request.setAttribute("message", "   К сожалению, таких мероприятий нет");
        }
        response.setContentType("application/json");

        // String json = new Gson().toJson(events);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        try {
            String json = objectMapper.writeValueAsString(events);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
