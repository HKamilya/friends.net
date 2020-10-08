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
    <meta charset="UTF-8">
    <title>AddEvent</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if ((request.getSession(false).getAttribute("User") == null)) {
%>
<jsp:forward page="/Login.jsp"></jsp:forward>
<%} %>
<body style="background: #6b6b6b">
<header>
    <jsp:include page="Header.jsp" />
</header>

<form name="form" action="AddEventServlet" method="post" onsubmit="return validate()" enctype="multipart/form-data">
    <table align="center">
        <tr>
            <td>Название</td>
            <td><label>
                <input type="text" name="name"/>
            </label></td>
        </tr>
        <tr>
            <td>Город</td>
            <td><label>
                <input type="text" name="city"/>
            </label></td>
        </tr>
        <tr>
            <td>Улица</td>
            <td><label>
                <input type="text" name="street"/>
            </label></td>
        </tr>
        <tr>
            <td>Дом</td>
            <td><label>
                <input type="text" name="house"/>
            </label></td>
        </tr>
        <tr>
            <td>Дата</td>
            <td><label>
                <input type="date" name="date"/>
            </label></td>
        </tr>
        <tr>
            <td>Image</td>
            <td><label>
                <input type="file" name="file" accept="image/*"/>
            </label></td>
        </tr>
        <tr>
            <td>Описание</td>
            <td><label>
                <input type="text" name="description"/>
            </label></td>
        </tr>
        <tr>
            <td>Категория</td>
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
