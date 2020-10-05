<%@ page import="ru.mvc.models.CategoriesDao" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.mvc.bean.Categories" %><%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 28.09.2020
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddEvent</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if ((request.getSession(false).getAttribute("User") == null)) {
%>
<jsp:forward page="/Login.jsp"></jsp:forward>
<%} %>
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
        %><%
        if (session.getAttribute("User") != null) {%>
        <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
        <%}%>
    </nav>
</header>

<form name="form" action="AddEventServlet" method="post" onsubmit="return validate()">
    <table align="center">
        <tr>
            <td>Name</td>
            <td><label>
                <input type="text" name="name"/>
            </label></td>
        </tr>
        <tr>
            <td>City</td>
            <td><label>
                <input type="text" name="city"/>
            </label></td>
        </tr>
        <tr>
            <td>Street</td>
            <td><label>
                <input type="text" name="street"/>
            </label></td>
        </tr>
        <tr>
            <td>House</td>
            <td><label>
                <input type="text" name="house"/>
            </label></td>
        </tr>
        <tr>
            <td>Image</td>
            <td><label>
                <input type="text" name="image"/>
            </label></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><label>
                <input type="text" name="description"/>
            </label></td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <label>
                    <select name="category"><% List<Categories> names = (List<Categories>) request.getAttribute("list");
                        if (names != null && !names.isEmpty()) {
                            for (Categories s : names) {%>
                        <option name="category_id" value="<%out.print(s.getId());%>"><%
                            out.print(s.getName());%></option>
                        <% }
                        }%>
                    </select>
                </label>
            </td>
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
</body>
</html>
