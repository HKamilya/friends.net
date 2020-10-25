<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>

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

        .row {
            margin: 0;
        }

        .helper {
            margin-left: 0;
            margin-right: 0;
        }

        .top {
            height: 150px;
        }

        .top2 {
            height: 150px;
        }
    </style>
    <title>Login</title>
    <div class="container">
        <div class="row align-items-center helper">
            <div class="col-12 top"></div>
            <div class="col-3">
            </div>
            <div class="col-6 login_block">
                <div class="col-12 top2"></div>
                <form method="post" action="/Login" name="form">
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-8">
                            <label for="usernm">Логин</label>
                            <br>
                            <input type="text" <#if username??>value="${username}" </#if> class="form-control"
                                   id="username" aria-describedby="emailHelp"
                                   name="username">
                            <br>
                            <br>
                            <label for="pass">Пароль</label>
                            <br>
                            <input type="password" class="form-control" id="password"
                                   <#if password??>value="${password}" </#if> name="password">
                            <br>
                            <br>
                            <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe"
                                   value="true">
                            <label class="form-check-label" for="exampleCheck1">Запомнить меня</label>
                            <br>
                            <span style="color:red">
                                <#if errMessage??>${errMessage}</#if></span>
                            <br>
                            <input type="submit" id="button" class="btn btn-secondary btn-lg" value="Войти">
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
</@base.main>