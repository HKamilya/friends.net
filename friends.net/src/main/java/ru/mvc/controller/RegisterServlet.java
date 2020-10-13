package ru.mvc.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.mvc.model.Events;
import ru.mvc.model.Users;
import ru.mvc.dao.UserDao;

public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.ftl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(b);
        }

        Users users = new Users();

        users.setFullName(fullName);
        users.setEmail(email);
        users.setUserName(username);
        users.setPassword(builder.toString());


        UserDao registerDao = new UserDao();

        String userRegistered = registerDao.registerUser(users);

        if (userRegistered.equals("SUCCESS")) {
            HttpSession session = request.getSession();
            UserDao userDao = new UserDao();
            Users user = userDao.getInfo(username);
            session.setMaxInactiveInterval(10 * 60);
            session.setAttribute("User", username);
            request.setAttribute("username", username);
            request.setAttribute("fullName", fullName);
            request.setAttribute("username", username);
            request.setAttribute("fullname", user.getFullName());
            request.setAttribute("description", user.getDescription());
            request.setAttribute("image", user.getImage());

            request.getRequestDispatcher("/user.ftl").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/register.ftl").forward(request, response);
        }
    }
}