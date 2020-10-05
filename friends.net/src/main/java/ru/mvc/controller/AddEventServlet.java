package ru.mvc.controller;

import ru.mvc.bean.Categories;
import ru.mvc.bean.Events;
import ru.mvc.models.EventDao;
import ru.mvc.models.CategoriesDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddEventServlet extends HttpServlet {

    public AddEventServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> catList = categoriesDao.getAllCategories();
        req.setAttribute("list", catList);
        for (Categories cat : catList) {
            System.out.println(cat.getName());
        }
        getServletContext().getRequestDispatcher("/AddEvent.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int categoryId = Integer.parseInt(request.getParameter("category"));

        //Copying all the input parameters in to local variables
        String username = (String) session.getAttribute("User");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        String status = "актуально";

        Events events = new Events();

        events.setName(name);
        events.setCity(city);
        events.setStreet(street);
        events.setHouse(house);
        events.setImage(image);
        events.setDescription(description);
        events.setCategory(categoryId);
        events.setStatus(status);

        EventDao eventDao = new EventDao();

        String userRegistered = eventDao.addEvent(username, events);

        if (userRegistered.equals("SUCCESS"))
        {
            request.getRequestDispatcher("/Main.jsp").forward(request, response);
        } else
        {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/AddEvent.jsp").forward(request, response);
        }
    }

}
