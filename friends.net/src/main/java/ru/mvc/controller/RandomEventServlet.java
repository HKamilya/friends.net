package ru.mvc.controller;

import ru.mvc.model.Events;
import ru.mvc.model.Request;
import ru.mvc.model.Review;
import ru.mvc.model.Users;
import ru.mvc.dao.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RandomEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String usernm = (String) session.getAttribute("User");
        req.setAttribute("user", usernm);

        EventDao eventDao = new EventDao();
        Events event = eventDao.getRandomEvent();
        UserDao userDao = new UserDao();
        Users user = userDao.findById(event.getUser().getId());
        req.setAttribute("event_id", event.getId());
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory().getName());
        req.setAttribute("date", event.getDatetime());
        req.setAttribute("author", user.getUserName());
        RequestDao requestDao = new RequestDao();
        List<Request> requests = requestDao.getAllRequests(event.getId());
        req.setAttribute("numOfReq", requests.size());

        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.getReviews(event.getId());
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/event.ftl").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
