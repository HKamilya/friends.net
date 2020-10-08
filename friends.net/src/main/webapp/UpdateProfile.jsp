<%--
  Created by IntelliJ IDEA.
  User: gipot
  Date: 08.10.2020
  Time: 15:06
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
    <link rel="stylesheet" href="style_menu_alternative.css">
    <meta charset="UTF-8">
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
            height: 40%;
            width: auto;
            background: #6B6B6B;
        }

        .space {
            background: white;
            padding: 20px 10px;
            display: flex;
            height: 17%;
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
            height: 90%;
            display: flex;
            background: #6B6B6B;
            text-align: left;
        }

        .about_text {
            font-family: "Times New Roman", serif;
            width: 100%;
            color: #161616;
        }
    </style>
    <title>Changing</title>

</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="profile">
    <div class="container">
        <div class="photo">
            <form>
                <div class="form-group">
                    <label for="ex">
                        File Input
                    </label>
                    <input type="file" class="form-control-file" id="ex">
                </div>
            </form>
        </div>
        <div class="inf">
            <div class="name">
                <input class="name_text" name="name" value="${fullname}">
            </div>
            <div class="space">
                <h3>О себе:</h3>
            </div>
            <div class="about" name="${AboutUser}">
                <input type="text" class="about_text" name="about">
            </div>
        </div>
    </div>
    <button type="submit" name="submit">Submit</button>
</div>

</body>
</html>
