package ru.mvc.controller;

import ru.mvc.bean.Events;
import ru.mvc.bean.Review;
import ru.mvc.models.EventDao;
import ru.mvc.models.ReviewDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String rev = request.getParameter("review");
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        Review review = new Review();

        review.setEvent_id(event_id);
        review.setText(rev);
        review.setEvent_id(event_id);

        ReviewDao reviewDao = new ReviewDao();

        String reviewAdded = reviewDao.addReview(username, review);

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
