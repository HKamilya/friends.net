package ru.mvc.controller;

import ru.mvc.model.Request;
import ru.mvc.dao.RequestDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String comment = request.getParameter("review");
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        Request req = new Request();
        req.setEvent_id(event_id);
        req.setComment(comment);

        RequestDao requestDao = new RequestDao();
        String requestSended = requestDao.addRequest(username, req);
        if (requestSended.equals("SUCCESS")) {
            request.getRequestDispatcher("/AllEvents.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", requestSended);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
