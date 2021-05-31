package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.ReviewDto;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Review;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.EventsService;
import ru.itis.javalab.services.RequestsService;
import ru.itis.javalab.services.ReviewsService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AddReviewController {
    @Autowired
    private ReviewsService reviewsService;

    @PostMapping(value = "/addReview")
    public String addReview(@RequestParam ReviewDto reviewDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewsService.save(reviewDto, userDetails.getId());

        return "redirect:/event?id=" + reviewDto.getEvent_id();
    }
}
