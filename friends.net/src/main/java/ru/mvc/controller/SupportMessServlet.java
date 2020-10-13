package ru.mvc.controller;

import ru.mvc.dao.SupportMessageDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.SupportMessage;
import ru.mvc.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SupportMessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String text = request.getParameter("text");
        String date = request.getParameter("date");
        System.out.println(date);
        String title = request.getParameter("title");
        String email = request.getParameter("email");
        if (email.equals("email")) {
            UserDao userdao = new UserDao();
            Users user = userdao.getInfo(username);
            email = user.getEmail();
        }
        SupportMessageDao supMessDao = new SupportMessageDao();
        SupportMessage suppMess = new SupportMessage();
        suppMess.setEmail(email);
        suppMess.setTitle(title);
        suppMess.setText(text);
        supMessDao.addMessage(suppMess);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);
    }
}
