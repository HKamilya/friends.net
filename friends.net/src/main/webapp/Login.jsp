<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 27.09.2020
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<header>
    <nav>
        <% if (session.getAttribute("User") != null) {%>
        <a href="${pageContext.request.contextPath}/AddEventServlet">Создать мероприятие</a>
        <%}%>
        <a href="">Поиск</a>
        <a href="${pageContext.request.contextPath}/AllEventsServlet">Все мероприятия</a>
        <a href="${pageContext.request.contextPath}/RandomEventServlet">Случайное мероприятие</a>
        <a href="Register.jsp">Регистрация</a>
        <%
            if (session.getAttribute("User") != null) {
                out.println("<a href=\"#\">" + (String) session.getAttribute("User") + "</a>");
            } else {
                out.println("<a href=\"/LoginServlet\">Войти</a>");
            }
        %><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
        <%
            if (session.getAttribute("User") != null) {%>
        <%}%>
    </nav>
</header>
<form name="form" action="<%=request.getContextPath()%>/LoginServlet" method="post">

    <table align="center">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Login"></input><input type="reset" value="Reset"></input></td>
        </tr>
    </table>
</form>
</body>
</html>