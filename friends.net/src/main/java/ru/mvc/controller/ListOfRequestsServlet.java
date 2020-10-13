package ru.mvc.controller;

import ru.mvc.model.Request;
import ru.mvc.dao.RequestDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListOfRequestsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String rev = request.getParameter("review");
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        RequestDao requestDao = new RequestDao();
        List<Request> requestsList = requestDao.getAllRequests(event_id);
        request.setAttribute("list", requestsList);
        getServletContext().getRequestDispatcher("/user.ftl").forward(request, response);

    }
}
