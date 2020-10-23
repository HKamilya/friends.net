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
        String usernm = (String) session.getAttribute("User");
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
        req.setAttribute("event_id", event.getId());
        req.setAttribute("name", event.getName());
        req.setAttribute("city", event.getCity());
        req.setAttribute("street", event.getStreet());
        req.setAttribute("house", event.getHouse());
        req.setAttribute("image", event.getImage());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("category", event.getCategory().getName().toString());
        req.setAttribute("date", event.getDate());
        req.setAttribute("time", event.getTime());
        req.setAttribute("numOfReq", requests.size());
        req.setAttribute("author", event.getUser().getUserName());
        req.setAttribute("currDate", currDate);

        int result = event.getDate().compareTo(currDate);

        req.setAttribute("diff", result);
        System.out.println(user.getUserName());
        ReviewDao reviewDao = new ReviewDao();
        List<Review> reviews = reviewDao.findAllByEventId(id);
        for (Review r : reviews) {
            System.out.println(r.getUser().getUserName());
        }
        req.setAttribute("reviewsList", reviews);
        getServletContext().getRequestDispatcher("/views/event.ftl").forward(req, resp);


    }
}
