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
    <title>Registration</title>
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
    <style>
        .container_reg {
            display: flex;
            width: 100%;
            height: 592px;
            background: #FF7373;
        }

        .rectangle {
            width: 400px;
            height: 455px;
            background: black;
            margin: 0 auto;
            margin-top: 100px;
        }

        .txt {
            font-family: "Roboto", sans-serif;
            font-size: 20px;
            color: white;

        }

        .register_form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

    </style>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="container_reg">
    <div class="rectangle">
        <div class="register_form" style="text-align: center">
            <form name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
                <label class="txt" for="fullnm"><b>Full Name</b></label>
                <div class="register_line">
                    <input type="text" id="fullnm" name="fullname"/>
                </div>
                <label class="txt" for="email"><b>Email</b></label>
                <div class="register_line">
                    <input type="text" id="email" name="email"/>
                </div>
                <label class="txt" for="usernm"><b>Username</b></label>
                <div class="register_line">
                    <input type="text" id="usernm" name="username"/>
                </div>
                <label class="txt" for="pass"><b>Password</b></label>
                <div class="register_line">
                    <input type="password" id="pass" name="password"/>
                </div>
                <label class="txt" for="confPass"><b>Confirm Password</b></label>
                <div class="register_line">
                    <input type="password" id="confPass" name="conpassword"/>
                </div>
                <p><%=(request.getAttribute("errMessage") == null) ? ""
                        : request.getAttribute("errMessage")%>
                </p>
                <input type="submit" class="btn btn-secondary" value="Зарегистрироваться"/>
            </form>
        </div>
    </div>
</div>

</body>
</html>
