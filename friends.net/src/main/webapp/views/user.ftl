<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<link rel="stylesheet" href="../css/user.css">
<@base.main>


    <div class="profile">
        <div class="container">
            <div class="photo">
                <#if image??>
                    <img class="img" src="/img?id=${image.id}" alt=" ">
                </#if>
            </div>
            <div class="inf">
                <div class="name">
                    <div class="name_text">
                        ${fullName}
                    </div>
                </div>
                <div class="space">
                    <h3>О себе:</h3>
                </div>
                <div class="about">
                    <div class="about_user">
                        <#if description??>
                            ${description}
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">

            <#if user==username>
                <form action="/UpdateProfile" style="float: right">
                    <button>Редактировать профиль</button>
                </form>

                <form action="/UserEvents" style="float: right">
                    <button>Мои мероприятия</button>
                </form>

            </#if>

        </div>

    </div>
</@base.main>
