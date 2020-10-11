<%@ page import="java.util.List" %>
<%@ page import="ru.mvc.model.Events" %><%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 01.10.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="css/style_menu_alternative.css">
<title>List</title>
<style>
    .main {
        display: flex;
        padding: 60px 0 0;
        flex-direction: column;
    }

    .event {
        width: 100%;
        display: flex;
        margin-bottom: 20px;
        align-items: center;
        background: black;
    }

    .name {
        font-family: 'Ravi Prakash', cursive;
        color: black;
        text-decoration: none;
        transition: color 0.4s linear;
    }

    .name:hover {
        color: lightsalmon;
    }

    .descr {
        font-family: "Roboto", sans-serif;
        align-content: center;
        width: 70%;
        color: white;
    }

    .h_p {
        width: 30%;
        height: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .img {
        width: 100%;
    }

    .photo {
        max-height: 100%;
        max-width: 90%;
    }
</style>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">
    <% List<Events> names = (List<Events>) request.getAttribute("list");
        if (names != null && !names.isEmpty()) {
            for (Events s : names) {%>
    <a class="name" href="${pageContext.request.contextPath}/EventServlet?id=<%out.println(s.getId());%>"><%
        out.println(s.getName());%></a>
    <div class="event">
        <div class="h_p">
            <div class="img">
                <%
                    out.println("<img class=\"photo\" src=\"" + s.getImage() + "\"" + ">");%>
            </div>
        </div>
        <div class="descr">
            <p><%out.println(s.getDescription());%></p>
        </div>
        <div class="descr">
            <p><%out.println(s.getCategory().getName());%></p>
        </div>
    </div>
    <% }
    }%>
</div>
</body>
</html>
