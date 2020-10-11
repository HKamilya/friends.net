package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Events;
import ru.mvc.model.Review;
import ru.mvc.dao.ReviewDao;
import ru.mvc.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

public class AddReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String rev = request.getParameter("review");
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        UserDao userDao = new UserDao();
        Users user = userDao.getInfo(username);
        Review review = new Review();
        EventDao eventDao = new EventDao();
        Events event = eventDao.findById(event_id);

        review.setEvent(event);
        review.setText(rev);
        review.setUser(user);
        System.out.println(user.getId() + " "+ event_id);

        ReviewDao reviewDao = new ReviewDao();

        String reviewAdded = reviewDao.addReview(review);

        if (reviewAdded.equals("SUCCESS")) {
            request.getRequestDispatcher("/Event.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", reviewDao);
            request.getRequestDispatcher("/Event.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
