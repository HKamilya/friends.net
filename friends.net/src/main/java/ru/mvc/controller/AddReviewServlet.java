package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.ReviewDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Event;
import ru.mvc.model.Review;
import ru.mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String rev = request.getParameter("review");
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        Review review = new Review();
        EventDao eventDao = new EventDao();
        Event event = eventDao.findById(event_id);

        review.setEvent_id(event);
        review.setText(rev);
        review.setUser_id(user);
        System.out.println(user.getId() + " " + event_id);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        review.setDate(formatForDateNow.format(dateNow));

        ReviewDao reviewDao = new ReviewDao();

        String reviewAdded = reviewDao.insert(review);

        if (reviewAdded.equals("SUCCESS")) {
            response.sendRedirect(request.getContextPath() + "/Event?id=" + event_id);
        } else {
            request.setAttribute("errMessage", reviewDao);
            response.sendRedirect(request.getContextPath() + "/Event?id=" + event_id);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        request.setAttribute("user", user);
    }
}
