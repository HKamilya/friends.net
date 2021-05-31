<link rel="stylesheet" href="/static/css/allEv.css">
<#import  "spring.ftl" as spring/>

<div class="main" id="v">
    <div id="vstavka">
    <#list list as event>
        <a class="name" href="/event?id=${event.id}">${event.name}</a>
        <div class="event">
            <div class="h_p">
                <div class="img">
                    <#if event.image??>
                        <img class="photo" src="/img?id=${event.image.id}">
                    </#if>
                </div>
            </div>
            <div class="descr">
                <p>${event.description}</p>
                <br>
                <p>${event.date}</p>
                <br><#if event.time??>
                <p>${event.time}</p>
            <br>
                </#if>
                <p>${event.category.name}</p>
            </div>

        </div>
    </#list>
    </div>
</div>
