package ru.mvc.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import ru.mvc.dao.ImageDao;
import ru.mvc.model.Image;
import ru.mvc.model.User;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        String fullname = request.getParameter("fullName");
        System.out.println(fullname);
        String description = request.getParameter("description");


        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String ext2 = FilenameUtils.getExtension(fileName);
        if (fileName.length() > 1) {
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
            Image image = user.getImage();
            image.setAddress(imgAddress);
            image.setType("image/" + ext2);
            imageDao.update(image);
            String v = imageDao.insert(image);
            image.setId(Integer.parseInt(v));
            user.setImage(image);
        }


        user.setDescription(description);
        user.setUsername(username);
        user.setFullname(fullname);
        String updatedUsersData = userDao.updateUsersData(user);

        if (updatedUsersData.equals("SUCCESS")) {

            response.sendRedirect(request.getContextPath() + "/Profile");
        } else {
            request.setAttribute("errMessage", updatedUsersData);
            response.sendRedirect(request.getContextPath() + "/Profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("User");
        request.setAttribute("user", username);
        UserDao userDao = new UserDao();
        User user = userDao.findByName(username);
        request.setAttribute("image", user.getImage());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("fullName", user.getFullname());
        request.setAttribute("description", user.getDescription());
        getServletContext().getRequestDispatcher("/views/updateProfile.ftl").forward(request, response);
    }
}
