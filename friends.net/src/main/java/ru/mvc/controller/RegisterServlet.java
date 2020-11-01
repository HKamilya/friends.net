package ru.mvc.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.mvc.model.User;
import ru.mvc.dao.UserDao;
import ru.mvc.util.Hashing;

public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.ftl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean passMatcher = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");

        boolean userNMMatcher = username.matches("^[a-zA-Z](.[a-zA-Z0-9_-]*)");
        if (!passMatcher | !userNMMatcher) {
            request.setAttribute("errMessage", "Введите корректные данные");
            request.getRequestDispatcher("/views/register.ftl").forward(request, response);
        } else {
            Hashing hashing = new Hashing();
            String hashPass = hashing.
                    hashing(password);

            User users = new User();

            users.setFullname(fullName);
            users.setEmail(email);
            users.setUsername(username);
            users.setPassword(hashPass);

            UserDao registerDao = new UserDao();

            String userRegistered = registerDao.insert(users);

            if (userRegistered.equals("SUCCESS")) {
                HttpSession session = request.getSession();
                UserDao userDao = new UserDao();
                User user = userDao.findByName(username);
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", user);
                request.setAttribute("user", user);
                request.setAttribute("username", user.getUsername());
                request.setAttribute("fullName", user.getFullname());
                request.setAttribute("username", user.getUsername());
                request.setAttribute("fullname", user.getFullname());
                request.setAttribute("description", user.getDescription());
                request.setAttribute("image", user.getImage());

                request.getRequestDispatcher("/views/user.ftl").forward(request, response);
            } else {
                request.setAttribute("errMessage", userRegistered);
                request.getRequestDispatcher("/views/register.ftl").forward(request, response);
            }
        }
    }
}