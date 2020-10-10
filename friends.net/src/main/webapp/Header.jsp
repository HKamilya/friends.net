<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 06.10.2020
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ravi+Prakash&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style_menu.css">
    <style>
        .nav__link {
            font-size: 14px;
        }
    </style>
</head>
<body>
<header class="header">
    <div class="container_main">
        <div class="header__inner">
            <div class="hl">Friends.net</div>
            <nav class="nav">
                <% if (session.getAttribute("User") != null) {%>
                <a class="nav__link" href="${pageContext.request.contextPath}/AddEventServlet">Организовать <br>
                    мероприятие</a>
                <%}%>
                <a class="nav__link" href="Search.jsp">Поиск</a>
                <div class="header__logo">
                    <img class="picture" src="img/man.png" alt="">
                </div>
                <a class="nav__link" href="${pageContext.request.contextPath}/AllEventsServlet">Интересные<br>мероприятия</a>
                <a class="nav__link" href="${pageContext.request.contextPath}/RandomEventServlet">Случайное<br>
                    мероприятие</a>
            </nav>
            <div class="header__auth">
                <%
                    if (session.getAttribute("User") != null) {%>
                <a class="auth__link" href="${pageContext.request.contextPath}/ProfileServlet"><%
                    out.println((String) session.getAttribute("User"));%></a>

                <% } else {
                    out.println("<a class=\"auth__link\" href=\"Register.jsp\">Регистрация</a>");
                    out.println("<a class=\"auth__link\" href=\"Login.jsp\">Войти</a>");
                }
                    if (session.getAttribute("User") != null) {%>
                <a href="<%=request.getContextPath()%>/LogoutServlet">Выйти</a>
                <%}%>
            </div>
        </div>
    </div>
</header>
</body>
</html>
