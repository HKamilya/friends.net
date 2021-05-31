package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.RequestDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Event;
import ru.mvc.model.Request;
import ru.mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserEventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String status = "";
        int status_id = Integer.parseInt(request.getParameter("status"));
        if (status_id == 1) {
            status = "актуально";
        } else {
            status = "неактуально";
        }
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        EventDao eventDao = new EventDao();
        Event event = new Event();
        event.setId(event_id);
        event.setStatus(status);
        eventDao.update(event);
        response.sendRedirect(request.getContextPath() + "/UserEvents");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        request.setAttribute("user", user);
        EventDao eventDao = new EventDao();
        List<Event> eventList = eventDao.findByUserId(user.getId());
        LinkedHashMap<Event, List<Request>> evReqList = new LinkedHashMap<>();
        for (Event event : eventList) {
            RequestDao requestDao = new RequestDao();
            List<Request> requests = requestDao.findAllByEventId(event.getId());
            evReqList.put(event, requests);
        }

        request.setAttribute("evReqList", evReqList);
        getServletContext().getRequestDispatcher("/views/userEvents.ftl").forward(request, response);

    }
}
