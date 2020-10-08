package ru.mvc.controller;

import ru.mvc.bean.Events;
import ru.mvc.bean.Request;
import ru.mvc.bean.Review;
import ru.mvc.models.EventDao;
import ru.mvc.models.RequestDao;
import ru.mvc.models.ReviewDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class RandomEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDao eventDao = new EventDao();
        Events event = eventDao.getRandomEvent();
        req.setAttribute("event_id", event.getId());
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory());
        req.setAttribute("date", event.getDatetime());

        RequestDao requestDao = new RequestDao();
        List<Request> requests = requestDao.getAllRequests(event.getId());
        req.setAttribute("numOfReq", requests.size());

        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.getReviews(event.getId());
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/Event.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
