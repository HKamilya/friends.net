package ru.mvc.controller;

import ru.mvc.model.Event;
import ru.mvc.dao.EventDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AllEventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);
        EventDao eventDao = new EventDao();
        List<Event> eventList = eventDao.getAllEvents();
        request.setAttribute("list", eventList);
        getServletContext().getRequestDispatcher("/allEvents.ftl").forward(request, response);
    }
}
