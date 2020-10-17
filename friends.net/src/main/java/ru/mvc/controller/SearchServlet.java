package ru.mvc.controller;

import ru.mvc.dao.CategoriesDao;
import ru.mvc.dao.EventDao;
import ru.mvc.model.Categories;
import ru.mvc.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        String search = request.getParameter("search");
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> categories = categoriesDao.getAllCategories();
        request.setAttribute("catList", categories);
        System.out.println(search);
        EventDao eventDao = new EventDao();
        List<Event> events = eventDao.findByName(search);
        request.setAttribute("list", events);
        getServletContext().getRequestDispatcher("/search.ftl").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> categories = categoriesDao.getAllCategories();
        EventDao eventDao = new EventDao();
        List<Event> eventList = eventDao.getAllEvents();
        request.setAttribute("catList", categories);
        request.setAttribute("list", eventList);
        getServletContext().getRequestDispatcher("/search.ftl").forward(request, response);

    }
}
