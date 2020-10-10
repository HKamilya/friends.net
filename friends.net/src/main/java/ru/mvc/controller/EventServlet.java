package ru.mvc.controller;

import ru.mvc.model.*;
import ru.mvc.dao.*;


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
        RequestDao requestDao = new RequestDao();
        UserDao userDao = new UserDao();

        Users user = userDao.findById(id);
        List<Request> requests = requestDao.getAllRequests(id);
        req.setAttribute("event_id", event.getUser_id());
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory().getName().toString());
        req.setAttribute("date", event.getDatetime());
        req.setAttribute("numOfReq", requests.size());
        req.setAttribute("author", user.getUserName());
        System.out.println(user.getUserName());
        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.getReviews(id);
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/Event.jsp").forward(req, resp);


    }
}
