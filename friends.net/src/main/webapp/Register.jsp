<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 22.09.2020
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register</title>
    <script>
        function validate() {
            var fullname = document.form.fullname.value;
            var email = document.form.email.value;
            var username = document.form.username.value;
            var password = document.form.password.value;
            var conpassword = document.form.conpassword.value;

            if (fullname == null || fullname == "") {
                alert("Full Name can't be blank");
                return false;
            } else if (email == null || email == "") {
                alert("Email can't be blank");
                return false;
            } else if (username == null || username == "") {
                alert("Username can't be blank");
                return false;
            } else if (password.length < 6) {
                alert("Password must be at least 6 characters long.");
                return false;
            } else if (password != conpassword) {
                alert("Confirm Password should match with the Password");
                return false;
            }
        }
    </script>
</head>
<body>
<header>
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
    %>
    <%
        if (session.getAttribute("User") != null) {%>
    <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
    <%
        }
    %>
</header>
<form name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
    <table align="center">
        <tr>
            <td>Full Name</td>
            <td><label>
                <input type="text" name="fullname"/>
            </label></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><label>
                <input type="text" name="email"/>
            </label></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><label>
                <input type="text" name="username"/>
            </label></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><label>
                <input type="password" name="password"/>
            </label></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><label>
                <input type="password" name="conpassword"/>
            </label></td>
        </tr>
        <tr>
            <td><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Register"/><input
                    type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>
