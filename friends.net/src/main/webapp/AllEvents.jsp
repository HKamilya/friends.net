<%@ page import="java.util.List" %>
<%@ page import="ru.mvc.bean.Events" %><%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 01.10.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllEvents</title>
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
        %>
        <%
            if (session.getAttribute("User") != null) {%>
        <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
        <%}%>
    </nav>
</header>
<ul><% List<Events> names = (List<Events>) request.getAttribute("list");
    if (names != null && !names.isEmpty()) {
        for (Events s : names) {%>
    <a href="${pageContext.request.contextPath}/EventServlet?id=<%out.println(s.getId());%>"><%out.println(s.getName());%></a><br>
    <% }
    }%>
</ul>
</body>
</html>
