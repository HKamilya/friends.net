package ru.mvc.controller;

import ru.mvc.model.*;
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

public class EventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User usernm = (User) session.getAttribute("User");
        req.setAttribute("user", usernm);

        EventDao eventDao = new EventDao();
        Integer id = Integer.parseInt(req.getParameter("id"));
        Event event = eventDao.findById(id);
        RequestDao requestDao = new RequestDao();
        UserDao userDao = new UserDao();

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String currDate = formatForDateNow.format(dateNow);

        User user = userDao.findById(id);
        List<Request> requests = requestDao.findAllByEventId(id);
        req.setAttribute("event", event);
        req.setAttribute("numOfReq", requests.size());
        req.setAttribute("currDate", currDate);

        int result = event.getDate().compareTo(currDate);

        req.setAttribute("diff", result);

        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.findAllByEventId(id);

        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/views/event.ftl").forward(req, resp);


    }
}
