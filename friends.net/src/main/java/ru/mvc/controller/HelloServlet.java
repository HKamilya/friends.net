package ru.mvc.controller;

import ru.mvc.dao.UserDao;
import ru.mvc.model.User;
import ru.mvc.util.Hashing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String login = "";
        String password = "";
        Integer id = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                login = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            } else if (cookie.getName().equals("id")) {
                id = Integer.parseInt(cookie.getValue());
            }
        }

        if (password != null && login != null) {
            UserDao userDao = new UserDao();

            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            user.setId(id);
            String mess = userDao.authenticateUser(user);
            if (mess.equals("User")) {
                user = userDao.findById(id);
                request.getSession().setAttribute("User", user.getUsername());
                request.setAttribute("user", login);
            }

        }

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/main.ftl");
        requestDispatcher.forward(request, response);

    }
}
