<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 27.09.2020
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <link rel="stylesheet" href="css/style_menu.css">
    <style>
        .row {
            width: 100%;
            margin: 0 auto;
        }

        .login_block {
            /*height: 500px;*/
            padding: 30px 0;
            background-color: #FF7373;
            text-align: center;
            border-radius: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            margin: -100px auto 0;
        }

        body {
            overflow-x: hidden;
        }

        .btn-primary, .btn-default, .btn-primary:hover {
            background-color: black;
            border-color: black;
        }

        .form-group {
            width: 350px;
        }

        .top {
            height: 150px;
        }

        .top2 {
            height: 150px;
        }
    </style>
    <title>Login</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="container">
    <div class="row align-items-center helper">
        <div class="col-12 top"></div>
        <div class="col-2">
        </div>
        <div class="col-6 login_block">
            <div class="col-12 top2"></div>
            <form method="post" action="<%=request.getContextPath()%>/LoginServlet" name="form">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <label for="usernm">Логин</label>
                        <br>
                        <input type="text" class="form-control" id="usernm" aria-describedby="emailHelp"
                               name="username">
                        <br>
                        <br>
                        <label for="pass">Пароль</label>
                        <br>
                        <input type="password" class="form-control" id="pass" name="password">
                        <br>
                        <br>
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Запомнить меня</label>
                        <br>
                        <span
                                style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>

                        <br>
                        <input type="submit" class="btn btn-secondary btn-lg" value="Войти">
                        <input type="reset" class="btn btn-secondary btn-lg" value="Сбросить">
                    </div>
                    <div class="col-2"></div>
                </div>
            </form>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
</body>
</html>