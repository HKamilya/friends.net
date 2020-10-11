package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Events;
import ru.mvc.model.Request;
import ru.mvc.dao.RequestDao;
import ru.mvc.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String comment = request.getParameter("comment");
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        UserDao userDao = new UserDao();
        Users user = userDao.getInfo(username);
        Request req = new Request();
        EventDao eventDao = new EventDao();
        Events event = eventDao.findById(event_id);
        req.setEvent(event);
        req.setComment(comment);
        req.setSubscriber(user);
        System.out.println(user);
        System.out.println(event);
        RequestDao requestDao = new RequestDao();
        String requestSended = requestDao.addRequest( req);
        if (requestSended.equals("SUCCESS")) {
            request.getRequestDispatcher("/Event.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", requestSended);
            request.getRequestDispatcher("/Event.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
