<%@ page import="ru.mvc.model.Review" %>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style_menu.css">
    <title>Event</title>
    <style>

        .img-sm {
            width: 46px;
            height: 46px;
        }

        .panel {
            box-shadow: 0 2px 0 rgba(0, 0, 0, 0.075);
            border-radius: 0;
            border: 0;
            margin-bottom: 15px;
        }

        .panel .panel-footer, .panel > :last-child {
            border-bottom-left-radius: 0;
            border-bottom-right-radius: 0;
        }

        .panel .panel-heading, .panel > :first-child {
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .panel-body {
            padding: 25px 20px;
        }

        .media-block .media-left {
            display: block;
            float: left
        }

        .media-block .media-right {
            float: right
        }

        .media-block .media-body {
            display: block;
            overflow: hidden;
            width: auto
        }

        .middle .media-left,
        .middle .media-right,
        .middle .media-body {
            vertical-align: middle
        }

        .thumbnail {
            border-radius: 0;
            border-color: #e9e9e9
        }

        .tag.tag-sm, .btn-group-sm > .tag {
            padding: 5px 10px;
        }

        .tag:not(.label) {
            background-color: #fff;
            padding: 6px 12px;
            border-radius: 2px;
            border: 1px solid #cdd6e1;
            font-size: 12px;
            line-height: 1.42857;
            vertical-align: middle;
            -webkit-transition: all .15s;
            transition: all .15s;
        }

        .text-muted, a.text-muted:hover, a.text-muted:focus {
            color: #acacac;
        }

        .text-sm {
            font-size: 0.9em;
        }

        .text-5x, .text-4x, .text-5x, .text-2x, .text-lg, .text-sm, .text-xs {
            line-height: 1.25;
        }

        .btn-trans {
            background-color: transparent;
            border-color: transparent;
            color: #929292;
        }

        .btn-icon {
            padding-left: 9px;
            padding-right: 9px;
        }

        .btn-sm, .btn-group-sm > .btn, .btn-icon.btn-sm {
            padding: 5px 10px !important;
        }

        .mar-top {
            margin-top: 15px;
        }

        .event_profile {
            width: 100%;
            max-width: 1170px;
            height: 100%;
            max-height: 1000px;
            display: flex;
            margin: 0 auto;
            flex-direction: row;
            align-items: center;
        }

        .image {
            width: 100%;
            height: 100%;
        }

        .picture {
            height: 100%;
            width: 100%;
        }

        .text_block {
            width: 70%;
            display: flex;
            margin: 0 auto;
            flex-direction: column;
            align-items: center;
        }

        .name_attend {
            display: flex;
            flex-direction: row;
            width: 100%;
        }

        .name {
            width: 100%;
            font-family: "Times New Roman", sans-serif;
            font-size: large;
            padding: 10px 10px;
        }

        .attend {
            padding: 10px 10px;
            width: 150px;
        }

        .where_when {
            width: 100%;
            height: 5%;
            display: flex;
            flex-direction: column;
        }

        .when {
            font-family: Roboto, sans-serif;
            font-size: small;
            padding: 5px 5px;
        }

        .where {
            font-family: Roboto, sans-serif;
            font-size: small;
            padding: 5px 5px;
        }

        .inform {
            height: 60%;
            width: 100%;
            font-family: "Times New Roman", sans-serif;
            font-size: medium;
            padding: 10px 10px;
        }

        .tags {
            height: 10%;
            text-decoration: blink;
            text-decoration: underline;
            display: flex;
            flex-direction: row;
        }

        .tag {
            font-family: "Times New Roman", sans-serif;
            font-size: small;
            color: #219FFF;
        }

        .but {
            align-content: center;
            padding: 30px 30px;
        }
    </style>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="event_profile">
    <div class="row">
        <div class="col-4">
            <div class="image">
                <img class="picture" src="img/bfr.jpg" alt="">${image}
            </div>
        </div>
        <div class="col-8">
            <div class="text_block">
                <div class="name_attend">
                    <div class="name">
                        ${name}
                    </div>
                    <div class="attend">
                        уже идет
                        <br>
                        ${numOfReq}
                    </div>
                </div>
                <div class="where_when">
                    <div class="where">
                        ${city}, ${street}, ${house}
                    </div>
                    <div class="when">
                        ${date}
                    </div>
                </div>
                <div class="inform">
                    <p>${description}</p>
                </div>
                <div class="inform">
                    <p>${category}</p>
                </div>
                <div class="inform">
                    <a href="${pageContext.request.contextPath}/AnProfileServlet?username=${author}">${author}</a>
                </div>
                <div class="tags">
                    <%--                    <div class="tag">--%>
                    <%--                        Класс--%>
                    <%--                    </div>--%>
                    <%--                    <div class="tag">--%>
                    <%--                        Супер--%>
                    <%--                    </div>--%>
                    <%--                    <div class="tag">--%>
                    <%--                        Интересно--%>
                    <%--                    </div>--%>
                </div>
                <div class="but">
                    <button type="button" name="submit" data-toggle="modal" data-target="#exampleModal">Я пойду!
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="text-align: center">
                <% if (session.getAttribute("User") != null) {%>
                <form name="form" action="SendRequestServlet" method="post" onsubmit="return validate()">
                    <label style="font-size: 20px">Комментарий</label>
                    <br>
                    <textarea style="width: 200px;" type="text" name="comment"></textarea>

                    <input type="hidden" name="event_id" value="${event_id}">
                    <%=(request.getAttribute("errMessage") == null) ? ""
                            : request.getAttribute("errMessage")%>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-dark" value="Отправить"/><input
                        class="btn btn-dark" type="reset" value="Reset"/>
                </form>
                <%} else {%>
                <label style="font-size: 15px">
                    К сожалению, откликнуться могут только зарегистрированные пользователи :(
                    <br>
                    Пожалуйста, зарегистрируйтесь или авторизуйтесь, если у вас уже есть аккаунт на этом сайте
                </label>
                <input type="submit" class="btn btn-dark" onclick="location.href='Login.jsp'" value="Войти"/><input
                    class="btn btn-dark" type="reset" onclick="location.href='Register.jsp'"
                    value="Зарегистрироваться"/><%}%>
            </div>
        </div>
    </div>
</div>
<section class="container">
    <div class="row">

        <div class="col-md-12">
            <div class="panel">
                <div class="panel-body">
                    <%
                        if (session.getAttribute("User") != null) {%>
                    <form action="AddReviewServlet" method="post" onsubmit="return validate()">
                        <textarea class="form-control" rows="2" placeholder="Добавьте Ваш комментарий"
                                  name="review"></textarea>
                        <div class="mar-top clearfix">
                            <input type="hidden" name="event_id" value="${event_id}">
                            <%=(request.getAttribute("errMessage") == null) ? ""
                                    : request.getAttribute("errMessage")%>
                            <button class="btn btn-sm btn-primary pull-right" type="submit"><i
                                    class="fa fa-pencil fa-fw"></i> Добавить
                            </button>
                            <a class="btn btn-trans btn-icon fa fa-video-camera add-tooltip" href="#"></a>
                            <a class="btn btn-trans btn-icon fa fa-camera add-tooltip" href="#"></a>
                            <a class="btn btn-trans btn-icon fa fa-file add-tooltip" href="#"></a>
                        </div>
                    </form>
                    <%}%>
                </div>
            </div>
            <div class="panel">
                <div class="panel-body">
                    <ul><% List<Review> names = (List<Review>) request.getAttribute("reviewsList");
                        if (names != null && !names.isEmpty()) {
                            for (Review s : names) {%>
                        <div class="media-block">
                            <a class="media-left" href="#"><img class="img-circle img-sm" alt="Профиль пользователя"
                                                                src="https://bootstraptema.ru/snippets/icons/2016/mia/1.png"></a>
                            <div class="media-body">
                                <div class="mar-btm">
                                    <a href="#" class="btn-link text-semibold media-heading box-inline"><%
                                        out.print(s.getUsername());%></a>
                                </div>
                                <p><%out.print(s.getText());%></p>
                                <div class="pad-ver">
                                    <div class="btn-group">
                                        <a class="btn btn-sm btn-default btn-hover-success" href="#"><i
                                                class="fa fa-thumbs-up"></i></a>
                                        <a class="btn btn-sm btn-default btn-hover-danger" href="#"><i
                                                class="fa fa-thumbs-down"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }
                        }%>
                    </ul>
                </div>
            </div>
        </div>

    </div><!-- /.row -->
</section><!-- /.container -->
</body>
</html>
