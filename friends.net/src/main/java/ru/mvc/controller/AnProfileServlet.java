package ru.mvc.controller;

import ru.mvc.model.Event;
import ru.mvc.model.User;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AnProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usernm = (String) session.getAttribute("User");
        request.setAttribute("user", usernm);
        String username = request.getParameter("username");

        EventDao eventDao = new EventDao();
        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        List<Event> events = eventDao.findByUserId(user.getId());


        request.setAttribute("username", username);
        request.setAttribute("fullName", user.getFullName());
        request.setAttribute("description", user.getDescription());
        request.setAttribute("image", user.getImage());
        request.setAttribute("eventsList", events);
        request.getRequestDispatcher("/views/user.ftl").forward(request, response);

    }
}
