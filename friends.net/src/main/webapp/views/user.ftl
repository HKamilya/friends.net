<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>

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
            height: 350px;
        }

        .img {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }

        .inf {
            width: 70%;
            display: flex;
            flex-direction: column;
        }

        .name {
            padding: 10px 10px;
            display: flex;
            background: #6B6B6B;
            width: 100%;
            font-size: 20px;

        }

        .space {
            background: white;
            padding: 10px 10px;
            display: flex;
            height: 30%;
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
            height: 60%;
            display: flex;
            background: #6B6B6B;
        }

        .about_user {
            font-family: "Times New Roman", serif;
            color: #161616;
        }
    </style>

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
