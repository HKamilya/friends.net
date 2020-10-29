package ru.mvc.controller;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Event;
import ru.mvc.model.Request;
import ru.mvc.dao.RequestDao;
import ru.mvc.model.User;

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
       User user = (User) session.getAttribute("User");
        String comment = request.getParameter("comment");
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        Request req = new Request();
        EventDao eventDao = new EventDao();
        Event event = eventDao.findById(event_id);
        req.setEvent_id(event);
        req.setComment(comment);
        req.setSubscriber_id(user);
        RequestDao requestDao = new RequestDao();
        String requestSended = requestDao.insert(req);
        if (requestSended.equals("SUCCESS")) {
            response.sendRedirect(request.getContextPath() + "/Event?id=" + event_id);
        } else {
            request.setAttribute("errMessage", requestSended);
            response.sendRedirect(request.getContextPath() + "/Event?id=" + event_id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);
    }
}
