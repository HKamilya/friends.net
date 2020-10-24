package ru.mvc.controller;

import org.apache.commons.io.IOUtils;
import ru.mvc.dao.ImageDao;
import ru.mvc.model.Event;
import ru.mvc.model.Image;
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
import java.util.List;

public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String status = null;
        int status_id = Integer.parseInt(request.getParameter("status"));
        if (status_id == 1) {
            status = "актуально";
        } else {
            status = "неактуально";
        }
        int event_id = Integer.parseInt(request.getParameter("event_id"));

        EventDao eventDao = new EventDao();
        eventDao.updateStatus(event_id, status);
        response.sendRedirect(request.getContextPath() + "/UserEvents");

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

        List<Event> events = eventDao.findByUserId(user.getId());
        request.setAttribute("username", username);
        request.setAttribute("fullName", user.getFullname());
        request.setAttribute("description", user.getDescription());
        request.setAttribute("eventsList", events);
        request.setAttribute("image", image);


//        response.setContentType(image.getType());
//
//        IOUtils.copyLarge(
//                new FileInputStream(image.getAddress()),
//                response.getOutputStream()
//        );
        request.getRequestDispatcher("/views/user.ftl").forward(request, response);
    }
}
