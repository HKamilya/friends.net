package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.dto.RequestDto;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.RequestsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SendRequestController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private RequestsService requestsService;

    @RequestMapping(value = "/sendRequest", method = RequestMethod.POST)
    public String sendRequest(@RequestParam(value = "comment", required = false) String comment,
                              @RequestParam(value = "event_id") long event_id,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Optional<Event> event = eventsService.findById(event_id);
        if (event.isPresent()) {
            RequestDto request = RequestDto.builder()
                    .comment(comment)
                    .eventId(event_id)
                    .userId(userDetails.getId())
                    .build();
            requestsService.insert(request);
        }
        return "redirect:/event?id=" + event_id;
    }
}
