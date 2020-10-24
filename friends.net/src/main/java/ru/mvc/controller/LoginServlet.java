package ru.mvc.controller;

import ru.mvc.model.Event;
import ru.mvc.model.User;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;
import ru.mvc.util.Hashing;

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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.ftl");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        User loginBean = new User();


        Hashing hashing = new Hashing();
        String hashPass = hashing.hasing(password);
        loginBean.setUsername(username);
        loginBean.setPassword(hashPass);
        UserDao loginDao = new UserDao();
        try {
            String userValidate = loginDao.authenticateUser(loginBean);

            if (userValidate.equals("User_Role")) {
                UserDao userDao = new UserDao();
                User user = userDao.findByName(username);
                String id1 = String.valueOf(user.getId());
                System.out.println("User's Home");
                if (rememberMe) {
                    Cookie c = new Cookie("username", username);
                    Cookie p = new Cookie("password", password);
                    Cookie id = new Cookie("id", id1);
                    c.setMaxAge(24 * 60 * 60 * 31);
                    p.setMaxAge(24 * 60 * 60 * 31);
                    id.setMaxAge(24 * 60 * 60 * 31);
                    response.addCookie(c);
                    response.addCookie(p);
                    response.addCookie(id);
                    System.out.println(c.getValue() + " " + p.getValue() + " " + id.getValue() + " ");
                }

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", username);
                request.setAttribute("username", username);
                request.setAttribute("user", username);
                loginBean = loginDao.findByName(username);
                EventDao eventDao = new EventDao();
                List<Event> events = eventDao.findByUserId(loginBean.getId());
                request.setAttribute("image", loginBean.getImage());
                request.setAttribute("eventsList", events);
                request.setAttribute("description", loginBean.getDescription());
                request.setAttribute("fullName", loginBean.getFullname());
                request.getRequestDispatcher("/views/user.ftl").forward(request, response);
            } else {
                System.out.println("Error message = " + userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("/views/login.ftl").forward(request, response);
            }
        } catch (Exception e1) {
            throw new IllegalStateException(e1);
        }
    }
}