package ru.mvc.util;

import ru.mvc.dao.EventDao;
import ru.mvc.dao.RequestDao;
import ru.mvc.dao.UserDao;
import ru.mvc.model.Event;
import ru.mvc.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        User loginBean = new User();


        Hashing hashing = new Hashing();
        String hashPass = hashing.hashing(password);
        loginBean.setUsername(username);
        loginBean.setPassword(hashPass);
        UserDao loginDao = new UserDao();
        try {
            User user = loginDao.authenticateUser(loginBean);

            if (user != null) {
                if (rememberMe) {
                    Cookie c = new Cookie("username", username);
                    Cookie p = new Cookie("password", hashPass);
                    Cookie id = new Cookie("id", String.valueOf(user.getId()));
                    c.setMaxAge(24 * 60 * 60 * 31);
                    p.setMaxAge(24 * 60 * 60 * 31);
                    id.setMaxAge(24 * 60 * 60 * 31);
                    response.addCookie(c);
                    response.addCookie(p);
                    response.addCookie(id);
                }

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60 * 60);
                session.setAttribute("User", user);
                request.setAttribute("username", username);
                request.setAttribute("user", user);
                RequestDao requestDao = new RequestDao();
                List<Event> events = requestDao.findAllByUserId(user.getId());

                request.setAttribute("image", user.getImage());
                request.setAttribute("list", events);
                request.setAttribute("description", user.getDescription());
                request.setAttribute("fullName", user.getFullname());
                request.getRequestDispatcher("/views/user.ftl").forward(request, response);
            } else {
                request.setAttribute("errMessage", "Введены неправильные данные");

                request.getRequestDispatcher("/views/login.ftl").forward(request, response);
            }
        } catch (Exception e1) {
            throw new IllegalStateException(e1);
        }

    }
}
