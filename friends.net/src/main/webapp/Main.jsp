<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 28.09.2020
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
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
                out.println("<a href=\"Login.jsp\">Войти</a>");
            }
            if (session.getAttribute("User") != null) {%>
        <a href="<%=request.getContextPath()%>/LogoutServlet">Выйти</a>
        <%}%>
    </nav>
</header>
</body>
</html>
