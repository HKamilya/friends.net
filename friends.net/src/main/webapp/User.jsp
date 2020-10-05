<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 27.09.2020
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if ((request.getSession(false).getAttribute("User") == null)) {
%>
<jsp:forward page="Login.jsp"></jsp:forward>
<%} %>
<body>
<header>
    <% if (session.getAttribute("User") != null) {%>
    <a href="${pageContext.request.contextPath}/AddEventServlet">Создать мероприятие</a>
    <%}%>
    <a href="">Поиск</a>
    <a href="${pageContext.request.contextPath}/AllEventsServlet">Все мероприятия</a>
    <a href="${pageContext.request.contextPath}/RandomEventServlet">Случайное мероприятие</a>
    <% if (session.getAttribute("User") == null) {%>
    <a href="Register.jsp">Регистрация</a>
    <%}%>
    <%
        if (session.getAttribute("User") != null) {
            out.println("<a href=\"#\">" + (String) session.getAttribute("User") + "</a>");
        } else {
            out.println("<a href=\"Login.jsp\">Войти</a>");
        }
        if (session.getAttribute("User") != null) {%>
    <a href="<%=request.getContextPath()%>/LogoutServlet">Выйти</a>
    <%}%>
</header>
<h2>User's Home</h2>
Welcome
<%=request.getAttribute("username") %>
</body>
</html>
