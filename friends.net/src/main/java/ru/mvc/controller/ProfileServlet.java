package ru.mvc.controller;

import org.apache.commons.io.IOUtils;
import ru.mvc.dao.ImageDao;
import ru.mvc.dao.RequestDao;
import ru.mvc.model.Event;
import ru.mvc.model.Image;
import ru.mvc.model.Request;
import ru.mvc.model.User;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        RequestDao requestDao = new RequestDao();
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        requestDao.delete(event_id, user.getId());
        response.sendRedirect(request.getContextPath() + "/Profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");

        request.setAttribute("user", username);
        EventDao eventDao = new EventDao();



        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        ImageDao imageDao = new ImageDao();
        Image image = imageDao.findById(user.getImage().getId());
        RequestDao requestDao = new RequestDao();
        List<Event> eventsEv = requestDao.findAllByUserId(user.getId());
        List<Event> events = new ArrayList<>();
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String date = formatForDateNow.format(dateNow);

        for (int i = 0; i < eventsEv.size(); i++) {
            if (eventsEv.get(i).getDate().compareTo(date) >= 0) {
                events.add(eventsEv.get(i));
            }
        }
        request.setAttribute("username", username);
        request.setAttribute("fullName", user.getFullname());
        request.setAttribute("description", user.getDescription());
        request.setAttribute("list", events);
        request.setAttribute("image", image);


        request.getRequestDispatcher("/views/user.ftl").forward(request, response);
    }
}
