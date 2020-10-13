<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


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
            height: 100%;
            background: #FF7373;
        }

        .rectangle {
            width: 400px;
            height: 455px;
            background: #212529;
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

        .register_form {
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .form-error {
            color: #bf0000;
            text-align: left;
            font-size: 14px;
            padding: 10px 10px;
            margin-top: -10px;
            display: none;
        }

        .register_form input:valid:not(:placeholder-shown) {
            border-color: green;
        }

        .register_form input:invalid:not(:placeholder-shown) {
            border-color: #bf0000;
        }

        .register_form input:invalid:not(:placeholder-shown) + .form-error {
            display: block;
        }

    </style>

    <div class="container_reg">
        <div class="rectangle">
            <div class="register_form" style="text-align: center">
                <form name="form" action="Registration" method="post" onsubmit="return validate()">
                    <label class="txt" for="fullnm"><b>Имя</b></label>
                    <div class="register_line">
                        <input type="text"  id="fullnm" name="fullname"/>

                    </div>
                    <label class="txt" for="email"><b>Адрес электронной почты</b></label>
                    <div class="register_line">
                        <input type="email" id="email" name="email"/>
                    </div>
                    <label class="txt" for="usernm"><b>Логин</b></label>
                    <div class="register_line">
                        <input type="text" id="usernm" pattern="^[a-zA-Z](.[a-zA-Z0-9_-]*)" name="username"/>
                        <span class="form-error">Неправильная форма логина</span>
                    </div>
                    <label class="txt" for="pass"><b>Пароль</b></label>
                    <div class="register_line">
                        <input type="password" id="pass"
                                name="password"/>
                        <span class="form-error">Неправильная форма пароля</span>
                    </div>
                    <label class="txt" for="confPass"><b>Повторите пароль</b></label>
                    <div class="register_line">
                        <input type="password" id="confPass"
                                  name="conpassword"/>
                    </div>
                    <p><#if errMessage??> ${errMessage}</#if>
                    </p>
                    <input type="submit" class="btn btn-secondary" value="Зарегистрироваться"/>
                </form>
            </div>
        </div>
    </div>
</@base.main>