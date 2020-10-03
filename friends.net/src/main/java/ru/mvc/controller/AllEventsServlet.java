package ru.mvc.controller;

import ru.mvc.bean.Events;
import ru.mvc.models.EventDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllEventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDao eventDao = new EventDao();
        List<Events> eventsList = eventDao.getAllEvents();
        request.setAttribute("list", eventsList);
        getServletContext().getRequestDispatcher("/AllEvents.jsp").forward(request, response);
    }
}
