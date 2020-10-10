<%@ page import="ru.mvc.model.Events" %>
<%@ page import="java.util.List" %><%--
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
    <link rel="stylesheet" href="css/style_menu.css">
    <meta charset="UTF-8">
    <title>Profile</title>
    <style>
        .container {
            width: 100%;
            max-width: 1170px;
            margin: 0 auto;
            display: flex;
            flex-direction: row;
        }

        .profile {
            padding: 10px;
        }

        .photo {
            width: 30%;
            height: 10%;
        }

        .img {
            width: 100%;
            height: 100%;
        }

        .inf {
            width: 70%;
            display: flex;
            flex-direction: column;
        }

        .name {
            padding: 10px 10px;
            display: flex;
            height: 10%;
            width: auto;
            background: #6B6B6B;
        }

        .space {
            background: white;
            padding: 10px 10px;
            display: flex;
            height: 30%;
            width: auto;
            align-items: center;
        }

        .name_text {
            font-family: "Roboto Thin", serif;
            font-size: 30px;
            color: #161616;
        }

        .about {
            padding: 10px 10px;
            height: 60%;
            display: flex;
            background: #6B6B6B;
        }

        .about_user {
            font-family: "Times New Roman", serif;
            color: #161616;
        }
    </style>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if ((request.getSession(false).getAttribute("User") == null)) {
%>
<jsp:forward page="Login.jsp"></jsp:forward>
<%} %>
<body>
<jsp:include page="Header.jsp"/>
<div class="profile">
    <div class="container">
        <div class="photo">
            <img class="img" src="img/man.png">
        </div>
        <div class="inf">
            <div class="name">
                <div class="name_text">
                    ${fullname}
                </div>
            </div>
            <div class="space">
                <h3>О себе:</h3>
            </div>
            <div class="about">
                <div class="about_user">
                    ${description}
                </div>
            </div>
        </div>
    </div>
    <% if
    (session.getAttribute("User") == request.getAttribute("username")) {%>
    <form action="${pageContext.request.contextPath}/UpdateProfileServlet">
        <button>Редактировать профиль</button>
    </form>
    <%}%>

    <% List<Events> names = (List<Events>) request.getAttribute("eventsList");
        if (names != null && !names.isEmpty()) {
            for (Events s : names) {%>
    <a class="name" href="${pageContext.request.contextPath}/EventServlet?id=<%out.println(s.getId());%>"><%
        out.println(s.getName());%></a>
    <div class="event">
        <div class="h_p">
            <div class="img">
                <%out.println("<img class=\"photo\" src=\"" + s.getImage() + "\"" + ">");%>
            </div>
        </div>
        <div class="descr">
            <p><%out.println(s.getDescription());%></p>
        </div>
        <div class="descr">
            <p><%out.println(s.getCategory());%></p>
        </div>
        <div class="descr">
            <p><%out.println(s.getStatus());%></p>
        </div>
        <% if
        (session.getAttribute("User") == request.getAttribute("username")) {%>

        <form action="${pageContext.request.contextPath}/ProfileServlet" method="post">

            <input type="hidden" name="event_id" value="<%out.print(s.getId());%>">
            <select name="status">
                <option name="status_name" value="1">актуально</option>
                <option name="status_name" value="2">неактуально</option>
            </select>
            <button type="submit" name="submit">Сохранить</button>
        </form>
        <%}%>
    </div>

    <% }
    }%>
</div>
</body>
</html>
