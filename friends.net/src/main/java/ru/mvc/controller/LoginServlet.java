package ru.mvc.controller;

import ru.mvc.model.Event;
import ru.mvc.model.User;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();     // request is an instance of type
        String username = null;
        String password = null;
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            Cookie p = cookies[i];
            if (c.getName().equals("username")) {
                username = c.getValue();
            }
            if (p.getName().equals("password")) {
                password = p.getValue();
            }
        }
        if (username != null & password != null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
        }
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.ftl");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        User loginBean = new User();


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
        loginBean.setUserName(username);
        loginBean.setPassword(builder.toString());
        UserDao loginDao = new UserDao();
        try {
            String userValidate = loginDao.authenticateUser(loginBean);

            if (userValidate.equals("User_Role")) {
                System.out.println("User's Home");
                if (rememberMe) {
                    Cookie c = new Cookie("username", username);
                    Cookie p = new Cookie("password", password);
                    c.setMaxAge(24 * 60 * 60 * 31);
                    p.setMaxAge(24 * 60 * 60 * 31);
                    response.addCookie(c);
                    response.addCookie(p);
                }

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", username);
                request.setAttribute("username", username);
                request.setAttribute("user", username);
                loginBean = loginDao.getInfo(username);
                EventDao eventDao = new EventDao();
                List<Event> events = eventDao.getAllUsersEvents(loginBean.getId());
                request.setAttribute("eventsList", events);
                request.setAttribute("description", loginBean.getDescription());
                request.setAttribute("fullName", loginBean.getFullName());
                request.getRequestDispatcher("/user.ftl").forward(request, response);
            } else {
                System.out.println("Error message = " + userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("/login.ftl").forward(request, response);
            }
        } catch (Exception e1) {
            throw new IllegalStateException();
        }
    }
}