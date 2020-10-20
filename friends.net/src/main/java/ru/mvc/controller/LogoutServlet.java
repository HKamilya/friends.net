package ru.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //Fetch session object


//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("username") || cookie.getName().equals("password") || cookie.getName().equals("id")) {
//                cookie.setValue(null);
//                cookie.setPath(request.getContextPath());
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }

        if (session != null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
            request.setAttribute("errMessage", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.ftl");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }
}