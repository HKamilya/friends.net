package ru.mvc.controller;

import ru.mvc.bean.Events;
import ru.mvc.bean.Request;
import ru.mvc.models.EventDao;
import ru.mvc.models.RequestDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListOfRequsetsServlet extends HttpServlet {
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
        getServletContext().getRequestDispatcher("/User.jsp").forward(request, response);

    }
}
