package ru.mvc.controller;

import ru.mvc.model.Events;
import ru.mvc.model.Users;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AnProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        EventDao eventDao = new EventDao();
        UserDao userDao = new UserDao();
        Users user = userDao.getInfo(username);
        List<Events> events = eventDao.getAllUsersEvents(user.getId());


        request.setAttribute("username", user.getUserName());
        request.setAttribute("fullname", user.getFullName());
        request.setAttribute("description", user.getDescription());
        request.setAttribute("image", user.getImage());
        request.setAttribute("eventsList", events);
        request.getRequestDispatcher("/User.jsp").forward(request, response);

    }
}
