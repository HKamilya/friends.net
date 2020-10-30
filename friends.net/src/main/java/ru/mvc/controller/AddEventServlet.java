package ru.mvc.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import ru.mvc.dao.ImageDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.CategoriesDao;
import ru.mvc.model.Image;
import ru.mvc.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@MultipartConfig
public class AddEventServlet extends HttpServlet {

    public AddEventServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        CategoriesDao categoriesDao = new CategoriesDao();
        List<Categories> catList = categoriesDao.findAll();
        req.setAttribute("list", catList);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/views/addEvent.ftl").forward(req, resp);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int categoryId = Integer.parseInt(request.getParameter("category"));


        User user = (User) session.getAttribute("User");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String date = request.getParameter("date");

        String time = request.getParameter("time");


        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String ext2 = FilenameUtils.getExtension(fileName);
        String uploadDir = getServletConfig().getInitParameter("uploadDir");
        String imgAddress =
                UUID.randomUUID().toString() +
                        "-" +
                        filePart.getSubmittedFileName();
        IOUtils.copyLarge(
                filePart.getInputStream(),
                new FileOutputStream(uploadDir +
                        File.separator + imgAddress
                )
        );
        ImageDao imageDao = new ImageDao();
        Image image = new Image();
        image.setType("image/" + ext2);
        image.setAddress(imgAddress);
        imageDao.insert(image);
        String v = imageDao.insert(image);
        image.setId(Integer.parseInt(v));


        String description = request.getParameter("description");
        String status = "актуально";


        Event event = new Event();
        Categories categories = new Categories();
        categories.setId(categoryId);
        event.setName(name);
        event.setCity(city);
        event.setTime(time);
        event.setStreet(street);
        event.setHouse(house);
        event.setImage(image);
        event.setDescription(description);
        event.setCategory_id(categories);
        event.setStatus(status);
        event.setDate(date);
        EventDao eventDao = new EventDao();
        event.setUser_id(user);

        String eventAdded = eventDao.insert(event);

        if (eventAdded.equals("SUCCESS")) {
            response.sendRedirect(request.getContextPath() + "/AddEvent");
        } else {
            request.setAttribute("errMessage", eventAdded);
            response.sendRedirect(request.getContextPath() + "/AddEvent");
        }
    }

}
