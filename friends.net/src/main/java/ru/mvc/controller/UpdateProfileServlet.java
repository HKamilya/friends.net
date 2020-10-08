package ru.mvc.controller;

import ru.mvc.bean.Users;
import ru.mvc.models.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        UserDao userDao = new UserDao();
        Users users = userDao.getInfo(username);
        request.setAttribute("fullname", users.getFullName());
        request.setAttribute("AboutUser", users.getDescription());
        getServletContext().getRequestDispatcher("/User.jsp").forward(request, response);
    }
}
