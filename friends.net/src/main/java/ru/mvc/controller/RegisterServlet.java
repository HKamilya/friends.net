package ru.mvc.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.mvc.bean.Users;
import ru.mvc.models.RegisterDao;
 
public class RegisterServlet extends HttpServlet {
  
     public RegisterServlet() {
     }
 
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Copying all the input parameters in to local variables
         String fullName = request.getParameter("fullname");
         String email = request.getParameter("email");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
          
         Users users = new Users();

         users.setFullName(fullName);
         users.setEmail(email);
         users.setUserName(username);
         users.setPassword(password);
          
         RegisterDao registerDao = new RegisterDao();
          
          String userRegistered = registerDao.registerUser(users);
          
         if(userRegistered.equals("SUCCESS"))
         {
             HttpSession session = request.getSession();
             session.setMaxInactiveInterval(10*60);
             session.setAttribute("User", username);
             request.setAttribute("username", username);

             request.getRequestDispatcher("/User.jsp").forward(request, response);
            request.getRequestDispatcher("/User.jsp").forward(request, response);
         }
         else   //On Failure, display a meaningful message to the User.
         {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
         }
     }
}