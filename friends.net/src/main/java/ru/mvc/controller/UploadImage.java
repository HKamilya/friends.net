package ru.mvc.controller;

import org.apache.commons.io.IOUtils;
import ru.mvc.dao.ImageDao;
import ru.mvc.model.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@WebServlet("/img")
public class UploadImage extends HttpServlet {
    public final String UPLOAD_DIR = "C:\\Users\\gipot\\Desktop\\inf\\friends.net\\friends.net\\data";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ImageDao imageDao = new ImageDao();
        Image image = imageDao.findById(id);

        resp.setContentType(image.getType());

        IOUtils.copyLarge(
                new FileInputStream(UPLOAD_DIR + File.separator + image.getAddress()),
                resp.getOutputStream()
        );
    }
}
