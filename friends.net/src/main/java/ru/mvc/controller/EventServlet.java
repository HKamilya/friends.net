package ru.mvc.controller;

import ru.mvc.bean.Events;
import ru.mvc.bean.Review;
import ru.mvc.models.EventDao;
import ru.mvc.models.ReviewDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDao eventDao = new EventDao();
        Integer id = Integer.parseInt(req.getParameter("id"));
        Events event = eventDao.getEvent(id);
        req.setAttribute("event_id", id);
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory());

        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.getReviews(id);
        req.setAttribute("list", reviews);
        getServletContext().getRequestDispatcher("/Event.jsp").forward(req, resp);


    }
}
