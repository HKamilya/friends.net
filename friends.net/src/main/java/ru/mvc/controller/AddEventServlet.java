package ru.mvc.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.mvc.model.Categories;
import ru.mvc.model.Events;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.CategoriesDao;


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
        String description = request.getParameter("description");
        String status = "актуально";
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> multifiles = sf.parseRequest(request);
            for (FileItem item : multifiles) {
                item.write(new File("src/main/webapp/img" + item.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        EventDao eventDao = new EventDao();

        String userRegistered = eventDao.addEvent(username, events);

        if (userRegistered.equals("SUCCESS")) {
            request.getRequestDispatcher("/AllEvents.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/AddEvent.jsp").forward(request, response);
        }
    }

}
