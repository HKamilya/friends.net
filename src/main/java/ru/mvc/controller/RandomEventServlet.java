package ru.mvc.controller;

import ru.mvc.model.Event;
import ru.mvc.model.Request;
import ru.mvc.model.Review;
import ru.mvc.model.User;
import ru.mvc.dao.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RandomEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        req.setAttribute("user", user);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String currDate = formatForDateNow.format(dateNow);

        EventDao eventDao = new EventDao();
        Event event = eventDao.findRandomEvent();

        RequestDao requestDao = new RequestDao();
        List<Request> requests = requestDao.findAllByEventId(event.getId());
        req.setAttribute("event", event);
        req.setAttribute("numOfReq", requests.size());
        req.setAttribute("currDate", currDate);

        int result = event.getDate().compareTo(currDate);

        req.setAttribute("diff", result);
        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.findAllByEventId(event.getId());
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/views/event.ftl").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
