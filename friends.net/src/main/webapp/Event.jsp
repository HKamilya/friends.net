<%@ page import="ru.mvc.bean.Review" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 01.10.2020
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event</title>
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
                out.println("<a href=\"/Login.jsp\">Войти</a>");
            }
        %><%
        if (session.getAttribute("User") != null) {%>
        <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
        <%}%>
    </nav>
</header>
<p>Name: ${name}</p>
<p>City: ${city}</p>
<p>Address: ${street}, ${house}</p>
<p>image: ${image}</p>
<p>description: ${description}</p>
<%--<ul><% List<Review> names = (List<Review>) request.getAttribute("list");--%>
<%--    if (names != null && !names.isEmpty()) {--%>
<%--        for (Review s : names) {%>--%>
<%--   <p><%out.print(s.getReview());%></p>--%>
<%--    <% }--%>
<%--    }%>--%>
<%--</ul>--%>
<%
    if (session.getAttribute("User") != null) {%>
<form name="form" action="AddReviewServlet" method="post" onsubmit="return validate()">
    <table align="center">
        <tr>
            <td>Review</td>
            <td><label>
                <input type="text" name="review"/>
            </label></td>
            <input type="hidden" name="event_id" value="${event_id}">
        </tr>
        <tr>
            <td><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Register"></input><input
                    type="reset" value="Reset"></input></td>
        </tr>
    </table>
</form>
<%}%>
</body>
</html>
