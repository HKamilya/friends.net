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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> categories = categoriesDao.findAll();
        request.setAttribute("catList", categories);
        String username = (String) session.getAttribute("User");
        String search = request.getParameter("search");
        String[] names = request.getParameterValues("category");
        List<Event> events = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        EventDao eventDao = new EventDao();
        if (names.length == 1 & names[0].equals("0")) {
            events = eventDao.findByName(search);
        } else {
            for (int i = 1; i < names.length; i++) {
                list.add(Integer.parseInt(names[i]));
            }
            events = eventDao.findByNameAndCategory(search, list);
        }
        System.out.println(list);
        request.setAttribute("user", username);
        request.setAttribute("list", events);
        getServletContext().getRequestDispatcher("/search.ftl").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        request.setAttribute("user", user);
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> categories = categoriesDao.findAll();
        EventDao eventDao = new EventDao();
        List<Event> eventList = eventDao.findAll();
        request.setAttribute("catList", categories);
        request.setAttribute("list", eventList);
        getServletContext().getRequestDispatcher("/search.ftl").forward(request, response);

    }
}
