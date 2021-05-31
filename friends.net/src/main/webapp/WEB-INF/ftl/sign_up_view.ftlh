<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <link rel="stylesheet" href="/static/css/register.css">
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <style>
        .error {
            color: red;
        }
    </style>

    <div class="container_reg">
        <div class="rectangle">
            <div class="register_form" style="text-align: center">
                <br>
                <#if namesErrorMessage??>
                    <p class="error">${namesErrorMessage}</p>
                </#if>

                <@spring.bind "userForm"/>
                <form name="form" action="signUp" method="post">
                    <label class="txt" for="email"><b><@spring.message 'sign_up_page.registration.email'/></b></label>
                    <div class="register_line">
                        <@spring.formInput "userForm.email"/>
                    </div>
                    <label class="txt"
                           for="usernm"><b><@spring.message 'sign_up_page.registration.username'/></b></label>
                    <div class="register_line">
                        <input type="text" id="usernm" name="username"/>
                        <span class="form-error">Неправильная форма логина</span>
                    </div>
                    <label class="txt" for="pass"><b><@spring.message 'sign_up_page.registration.password'/></b></label>
                    <div class="register_line">
                        <@spring.formPasswordInput "userForm.password"/>
                        <br>
                    </div>
                    <@spring.showErrors "<br>" "error"/>
                    <br>
                    <label class="txt"
                           for="pass"><b><@spring.message 'sign_up_page.registration.password2'/></b></label>
                    <div class="register_line">
                        <@spring.formPasswordInput "userForm.confPass"/>
                        <br>
                    </div>
                    <br>
                    <input type="submit" class="btn btn-secondary"
                           value="<@spring.message 'sign_up_page.registration.button'/>"/>
                </form>
            </div>
        </div>
    </div>
</@base.main>
