package ru.mvc.controller;

import com.google.gson.Gson;
import ru.mvc.dao.EventDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String term = request.getParameter("term");
        EventDao eventDao = new EventDao();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(eventDao.findByName(term)));
        out.flush();
        out.close();
    }

}
