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
            height: 10%;
            width: auto;
            background: #6B6B6B;
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
                <img class="img" src="img/man.png">
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
                        ${description}
                    </div>
                </div>
            </div>
        </div>
        <#if user==username>
            <form action="/UpdateProfile">
                <button>Редактировать профиль</button>
            </form>
        </#if>
        <#list eventsList as eventsList>

            <a class="name" href="/EventServlet?id=${eventsList.id}">${eventsList.id}</a>
            <div class="event">
                <div class="h_p">
                    <div class="img">
                        <#if eventsList.image??>
                            <img class="photo" src="${eventsList.image}">
                        </#if>
                    </div>
                </div>
                <div class="descr">
                    <p>${eventsList.description}</p>
                </div>
                <div class="descr">
                    <p>${eventsList.category.name}</p>
                </div>
                <div class="descr">
                    <p>${eventsList.status}</p>
                </div>
                <#if user==username>
                    <form action="/Profile" method="post">
                        <input type="hidden" name="event_id" value="${eventsList.id}">
                        <select name="status">
                            <option name="status_name" value="1">актуально</option>
                            <option name="status_name" value="2">неактуально</option>
                        </select>
                        <button type="submit" name="submit">Сохранить</button>
                    </form>
                </#if>
            </div>
        </#list>
    </div>
</@base.main>
