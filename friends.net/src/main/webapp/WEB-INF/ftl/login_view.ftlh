<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <link rel="stylesheet" href="/static/css/login.css">

    <title>Login</title>
    <div class="container">
        <div class="row align-items-center helper">
            <div class="col-12 top"></div>
            <div class="col-3">
            </div>
            <div class="col-6 login_block">
                <div class="col-12 top2"></div>
                <form method="post" name="form">
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-8">
                            <label for="usernm"><@spring.message 'login_page.registration.username'/></label>
                            <br>
                            <input type="text" <#if username??>value="${username}" </#if> class="form-control"
                                   id="username" aria-describedby="emailHelp"
                                   name="username">
                            <br>
                            <br>
                            <label for="pass">
                                <@spring.message 'sign_up_page.registration.password'/></label>
                            <br>
                            <input type="password" class="form-control" id="password"
                                   <#if password??>value="${password}" </#if> name="password">
                            <br>
                            <br>
                            <input type="checkbox" name="remember-me" class="form-check-input"
                                   id="rememberMe">
                            <label class="form-check-label"
                                   for="exampleCheck1"><@spring.message 'login_page.form.remember'/></label>
                            <br>
                            <span style="color:red">
                                <#if errMessage??>${errMessage}</#if></span>
                            <br>
                            <input type="submit" id="button" class="btn btn-secondary btn-lg"
                                   value="<@spring.message 'header_page.registration.logIn'/>">
                            <input type="reset" class="btn btn-secondary btn-lg"
                                   value="<@spring.message 'add_event_page.form.reset'/>">
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