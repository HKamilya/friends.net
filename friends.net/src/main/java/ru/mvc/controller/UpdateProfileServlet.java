package ru.mvc.controller;

import ru.mvc.model.Users;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String fullname = request.getParameter("fullName");
        String description = request.getParameter("description");
        UserDao userDao = new UserDao();
        Users user = new Users();
        user.setDescription(description);
        user.setUserName(username);
        user.setFullName(fullname);
        String updatedUsersData = userDao.updateUsersData(user);

        if (updatedUsersData.equals("SUCCESS")) {

            request.getRequestDispatcher("/User.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", updatedUsersData);
            request.getRequestDispatcher("/User.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        UserDao userDao = new UserDao();
        Users users = userDao.getInfo(username);
        request.setAttribute("password", users.getPassword());
        request.setAttribute("fullName", users.getFullName());
        request.setAttribute("description", users.getDescription());
        getServletContext().getRequestDispatcher("/UpdateProfile.jsp").forward(request, response);
    }
}
