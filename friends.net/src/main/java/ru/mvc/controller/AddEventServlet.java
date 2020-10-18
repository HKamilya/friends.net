package ru.mvc.controller;

import ru.mvc.dao.UserDao;
import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.dao.EventDao;
import ru.mvc.dao.CategoriesDao;
import ru.mvc.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        List<Categories> catList = categoriesDao.findAll();
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


        Part filePart = request.getPart("image"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        String imgName = "img\\" + username + fileName;
        String pathName = "C:\\Users\\gipot\\Desktop\\inf\\friends.net\\friends.net\\src\\main\\webapp\\img\\" + username + fileName;
        File file = new File(pathName);
        boolean created = file.createNewFile();
        OutputStream os = new FileOutputStream(pathName);

        byte[] b = new byte[2048];
        int length;

        while ((length = fileContent.read(b)) != -1) {
            os.write(b, 0, length);
        }

        fileContent.close();
        os.close();


        String description = request.getParameter("description");
        String status = "актуально";


        Event event = new Event();
        Categories categories = new Categories();
        categories.setId(categoryId);
        event.setName(name);
        event.setCity(city);
        event.setStreet(street);
        event.setHouse(house);
        event.setImage(imgName);
        event.setDescription(description);
        event.setCategory(categories);
        event.setStatus(status);
        event.setDate(date);
        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        EventDao eventDao = new EventDao();
        event.setUser(user);

        String userRegistered = eventDao.insert(event);

        if (userRegistered.equals("SUCCESS")) {
            request.getRequestDispatcher("/addEvent.ftl").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/addEvent.ftl").forward(request, response);
        }
    }

}
