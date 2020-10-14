package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Event;
import ru.mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserEventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        request.setAttribute("user", username);
        UserDao userDao = new UserDao();
        User user = userDao.getInfo(username);
        EventDao eventDao = new EventDao();
        List<Event> eventList = eventDao.getAllUsersEvents(user.getId());
        request.setAttribute("list", eventList);


        getServletContext().getRequestDispatcher("/userEvents.ftl").forward(request, response);

    }
}
