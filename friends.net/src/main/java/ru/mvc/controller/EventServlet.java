package ru.mvc.controller;

import ru.mvc.model.*;
import ru.mvc.dao.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String usernm = (String) session.getAttribute("User");
        req.setAttribute("user", usernm);

        EventDao eventDao = new EventDao();
        Integer id = Integer.parseInt(req.getParameter("id"));
        Event event = eventDao.findById(id);
        RequestDao requestDao = new RequestDao();
        UserDao userDao = new UserDao();

        User user = userDao.findById(id);
        List<Request> requests = requestDao.getAllRequests(id);
        req.setAttribute("event_id", event.getId());
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory().getName().toString());
        req.setAttribute("date", event.getDate());
        req.setAttribute("numOfReq", requests.size());
        req.setAttribute("author", event.getUser().getUserName());
        System.out.println(user.getUserName());
        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.getReviews(id);
        for (Review r : reviews) {
            System.out.println(r.getUser().getUserName());
        }
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/event.ftl").forward(req, resp);


    }
}
