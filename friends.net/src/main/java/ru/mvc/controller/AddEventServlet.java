package ru.mvc.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Categories;
import ru.mvc.model.Events;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.CategoriesDao;
import ru.mvc.model.Users;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class AddEventServlet extends HttpServlet {

    public AddEventServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("User");
        req.setAttribute("user", user);
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> catList = categoriesDao.getAllCategories();
        req.setAttribute("list", catList);
        for (Categories cat : catList) {
            System.out.println(cat.getName());
        }
        getServletContext().getRequestDispatcher("/addEvent.ftl").forward(req, resp);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int categoryId = Integer.parseInt(request.getParameter("category"));

        //Copying all the input parameters in to local variables
        String username = (String) session.getAttribute("User");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        String status = "актуально";


        Events events = new Events();
        Categories categories = new Categories();
        categories.setId(categoryId);
        events.setName(name);
        events.setCity(city);
        events.setStreet(street);
        events.setHouse(house);
//        events.setImage(image);
        events.setDescription(description);
        events.setCategory(categories);
        events.setStatus(status);
        events.setDate(date);
        UserDao userDao = new UserDao();
        Users user = userDao.getInfo(username);
        EventDao eventDao = new EventDao();
        events.setUser(user);

        String userRegistered = eventDao.addEvent(events);

        if (userRegistered.equals("SUCCESS")) {
            request.getRequestDispatcher("/addEvent.ftl").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/addEvent.ftl").forward(request, response);
        }
    }

}
