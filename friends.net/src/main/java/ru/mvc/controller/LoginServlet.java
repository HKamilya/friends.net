package ru.mvc.controller;

import ru.mvc.bean.Users;
import ru.mvc.models.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users loginBean = new Users();


        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b:bytes) {
            builder.append(b);
        }
        loginBean.setUserName(username);
        loginBean.setPassword(builder.toString());
        UserDao loginDao = new UserDao();
        try {
            String userValidate = loginDao.authenticateUser(loginBean);

            if (userValidate.equals("User_Role")) {
                System.out.println("User's Home");

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", username);
                request.setAttribute("username", username);
                loginBean = loginDao.getInfo(username);
                System.out.println(loginBean.getDescription());
                request.setAttribute("description", loginBean.getDescription());
                request.setAttribute("fullname", loginBean.getFullName());
                request.getRequestDispatcher("/User.jsp").forward(request, response);
            } else {
                System.out.println("Error message = " + userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("/Login.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}