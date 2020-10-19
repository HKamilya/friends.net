package ru.mvc.controller;

import org.apache.commons.io.FilenameUtils;
import ru.mvc.model.User;
import ru.mvc.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;

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
            InputStream fileContent = filePart.getInputStream();
            String imgName = "img\\profileimg" + username + "." + ext2;
            String pathName = "C:\\Users\\gipot\\Desktop\\inf\\friends.net\\friends.net\\src\\main\\webapp\\img\\profileimg" + username + "." + ext2;
            File file = new File(pathName);
            boolean created = file.createNewFile();
            OutputStream os = new FileOutputStream(pathName);

            byte[] b = new byte[2048];
            int length;

            while ((length = fileContent.read(b)) != -1) {
                os.write(b, 0, length);
            }
            os.close();
            user.setImage(imgName);
            fileContent.close();
        }


        user.setDescription(description);
        user.setUserName(username);
        user.setFullName(fullname);
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
        request.setAttribute("fullName", user.getFullName());
        request.setAttribute("description", user.getDescription());
        getServletContext().getRequestDispatcher("/views/updateProfile.ftl").forward(request, response);
    }
}
