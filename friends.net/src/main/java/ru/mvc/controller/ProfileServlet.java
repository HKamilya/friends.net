package ru.mvc.controller;

import ru.mvc.bean.Users;
import ru.mvc.models.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        UserDao userDao = new UserDao();
        Users user = userDao.getInfo(username);
        request.setAttribute("username", username);
        request.setAttribute("fullname", user.getFullName());
        request.setAttribute("description", user.getDescription());
        request.setAttribute("image", user.getImage());
        request.getRequestDispatcher("/User.jsp").forward(request, response);

    }
}
