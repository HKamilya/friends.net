package ru.mvc.controller;

import com.google.gson.Gson;
import ru.mvc.dao.CategoriesDao;
import ru.mvc.dao.EventDao;
import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.model.User;

import javax.lang.model.element.ElementVisitor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
//        CategoriesDao categoriesDao = new CategoriesDao();
//        List<Categories> categories = categoriesDao.findAll();
//        request.setAttribute("catList", categories);
//        String username = (String) session.getAttribute("User");
//        String search = request.getParameter("search");
//        String[] names = request.getParameterValues("category");
//        String date = request.getParameter("date");
//        System.out.println("lfnf" + date);
//        List<Event> events = new ArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        EventDao eventDao = new EventDao();
//        System.out.println(Arrays.toString(names));
//        if (names.length > 1 & date.length() > 1 & search.length() != 0) { //поиск по категориям, имени и дате или по категории и дате
//            for (int i = 1; i < names.length; i++) {
//                list.add(Integer.parseInt(names[i]));
//            }
//            events = eventDao.findByNameAndCategoryAndDate(search, list, date);
//        } else if (names.length > 1 & date.length() < 1) { // поиск по категориям и  имени и просто категориям
//            for (int i = 1; i < names.length; i++) {
//                list.add(Integer.parseInt(names[i]));
//            }
//            events = eventDao.findByNameAndCategory(search, list);
//        } else if (names.length == 1 & names[0].equals("0") & search.length() > 0 & date.length() > 0) {
//            events = eventDao.findByNameAndDate(search, date);
//        } else if (date.length() < 1 & names.length == 1 & names[0].equals("0")) {//поиск по имени
//            events = eventDao.findByName(search);
//        } else if (names.length == 1 & names[0].equals("0") & search.length() == 0 & date.length() > 1) {
//            events = eventDao.findByDate(date);
//        }
//        if (events.size() == 0) {
//            request.setAttribute("message", "   К сожалению, таких мероприятий нет");
//        }
//
//        request.setAttribute("user", username);
//        request.setAttribute("list", events);
//
//        getServletContext().getRequestDispatcher("/views/search.ftl").forward(request, response);
//

        String search = request.getParameter("search");
        System.out.println(search);
        String s = request.getParameter("tags");
        String date = request.getParameter("date");
        System.out.println("tags" + s);
        EventDao eventDao = new EventDao();
        List<Event> events = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        String[] tags;
        tags = s.split(",");
        if (!s.equals("[]")) {
            for (int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].replace("[", "");
                tags[i] = tags[i].replace("\"", "");
                tags[i] = tags[i].replace("]", "");
            }
        }
        if (!s.equals("[]") & date.length() > 1) { //поиск по категориям, имени и дате или по категории и дате
            for (String tag : tags) {
                list.add(Integer.parseInt(tag));
            }
            events = eventDao.findByNameAndCategoryAndDate(search, list, date);
        } else if (!s.equals("[]") & date.length() < 1) { // поиск по категориям и  имени и просто категориям
            for (String tag : tags) {
                list.add(Integer.parseInt(tag));
            }
            events = eventDao.findByNameAndCategory(search, list);
        } else if (s.equals("[]") & search.length() > 0 & date.length() > 0) {
            events = eventDao.findByNameAndDate(search, date);
        } else if (date.length() < 1 & s.equals("[]")) {//поиск по имени
            events = eventDao.findByName(search);
        } else if (s.equals("[]") & search.length() == 0 & date.length() > 1) {
            events = eventDao.findByDate(date);
        }
        if (events.size() == 0) {
            request.setAttribute("message", "   К сожалению, таких мероприятий нет");
        }
        response.setContentType("application/json");
        String json = new Gson().toJson(events);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        request.setAttribute("user", user);
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> categories = categoriesDao.findAll();
        EventDao eventDao = new EventDao();
        List<String> eventsNames = new ArrayList<>();
        List<Event> eventList = eventDao.findAll();
        for (Event ev : eventList) {
            eventsNames.add(ev.getName());
        }
        eventList.sort((o1, o2) -> (o2.getDate().compareTo(o1.getDate())));
        String json = new Gson().toJson(eventsNames);
        request.setAttribute("names", json);
        request.setAttribute("catList", categories);
        request.setAttribute("list", eventList);
        getServletContext().getRequestDispatcher("/views/search.ftl").forward(request, response);

    }
}
