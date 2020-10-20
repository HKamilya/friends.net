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
        String date = request.getParameter("date");
        System.out.println("lfnf" + date);
        List<Event> events = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        EventDao eventDao = new EventDao();
        System.out.println(Arrays.toString(names));
        if (names.length > 1 & date.length() > 1 & search.length() != 0) { //поиск по категориям, имени и дате или по категории и дате
            for (int i = 1; i < names.length; i++) {
                list.add(Integer.parseInt(names[i]));
                System.out.println("oh");
            }
            events = eventDao.findByNameAndCategoryAndDate(search, list, date);
        } else if (names.length > 1 & date.length() < 1) { // поиск по категориям и  имени и просто категориям
            for (int i = 1; i < names.length; i++) {
                list.add(Integer.parseInt(names[i]));
            }
            events = eventDao.findByNameAndCategory(search, list);
        } else if (names.length == 1 & names[0].equals("0") & search.length() > 0 & date.length() > 0) {
            events = eventDao.findByNameAndDate(search, date);
        } else if (date.length() < 1 & names.length == 1 & names[0].equals("0")) {//поиск по имени
            events = eventDao.findByName(search);
        } else if (names.length == 1 & names[0].equals("0") & search.length() == 0 & date.length() > 1) {
            events = eventDao.findByDate(date);
        }
        if (events.size() == 0) {
            request.setAttribute("message", "   К сожалению, таких мероприятий нет");
        }

        request.setAttribute("user", username);
        request.setAttribute("list", events);

        getServletContext().getRequestDispatcher("/views/search.ftl").forward(request, response);


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
        getServletContext().getRequestDispatcher("/views/search.ftl").forward(request, response);

    }
}
