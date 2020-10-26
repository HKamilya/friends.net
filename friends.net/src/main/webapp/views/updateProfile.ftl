<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<link rel="stylesheet" href="../css/style_menu_alternative.css">
<link rel="stylesheet" href="../css/updateProfile.css">
<@base.main>
    <#if user??>
        <script>
            var maxCount = 300;
            var redCount = 3;
            var input = $("#description");

            $("#count").text(maxCount);

            function getCount() {
                var count = maxCount - $("#description").val().length;
                $("#count").text(count);
                if (count <= redCount) {
                    $(".inform-text").addClass("red");
                } else if (count > 0 && $(".inform-text").hasClass("red")) {
                    $(".inform-text").removeClass("red");
                    $("#submit-button").removeClass("disabled");
                }
                if (count <= 0) {
                    $("#submit-button").addClass("disabled");
                    $("#submit-button").text("Недоступно");
                } else if (count > 0 && $("#submit-button").hasClass("disabled")) {
                    $("#submit-button").removeClass("disabled");
                    $("#submit-button").text("Отправить");
                }
            }
        </script>


        <div class="profile">
            <form action="/UpdateProfile" method="post" enctype="multipart/form-data">
                <div class="container">
                    <div class="photo">
                        <div class="form-group">
                            <label for="ex">
                                File Input
                            </label>
                            <input type="file" name="image" class="form-control-file" id="ex"
                                   value="<#if image??>${image}</#if>">
                        </div>

                    </div>
                    <div class="inf">
                        <div class="name">
                            <input class="name_text" name="fullName" value="${fullName}">
                        </div>
                        <div class="space">
                            <h3>О себе:</h3>
                        </div>
                        <div class="about">
                            <input type="text" autocomplete="off"  class="about_text" id="description" name="description"
                                   value="<#if description??>${description}</#if>">

                        </div>
                        <p class="inform-text">Количество оставшихся символов: <span id="count"></span></p>

                    </div>
                </div>
                <button type="submit" id="submit-button" name="submit">Сохранить</button>
            </form>
            <#--            <form method="post" action="">-->
            <#--                <label class="txt" for="pass"><b>Старый пароль</b></label>-->
            <#--                <div class="register_line">-->
            <#--                    <input type="password" id="earlpass" name="earlpass"/>-->
            <#--                </div>-->
            <#--                <label class="txt" for="pass"><b>Новый пароль</b></label>-->
            <#--                <div class="register_line">-->
            <#--                    <input type="password" id="pass" name="password"/>-->
            <#--                </div>-->
            <#--                <label class="txt" for="confPass"><b>Повторите пароль</b></label>-->
            <#--                <div class="register_line">-->
            <#--                    <input type="password" id="confPass" name="conpassword"/>-->
            <#--                </div>-->
            <#--            </form>-->
        </div>
    </#if>
</@base.main>
