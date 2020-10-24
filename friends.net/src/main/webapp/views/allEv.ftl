<style>
    .main {
        display: flex;
        padding: 60px 0 0;
        flex-direction: column;
    }

    .event {
        width: 100%;
        display: flex;
        margin-bottom: 20px;
        align-items: center;
        background: black;
    }

    .name {
        font-family: 'Ravi Prakash', cursive;
        color: black;
        font-size: larger;
        text-decoration: none;
        transition: color 0.4s linear;
    }

    .name:hover {
        color: lightsalmon;
    }

    .descr {
        font-family: "Roboto", sans-serif;
        align-content: center;
        width: 70%;
        color: white;
    }

    .h_p {
        width: 30%;
        height: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .img {
        width: 100%;
    }

    .photo {
        max-height: 100%;
        max-width: 90%;
    }
</style>

<div class="main"><#list list as event>
        <a class="name" href="/Event?id=${event.id}">${event.name}</a>
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
                <p>${event.category_id.name}</p>
            </div>

        </div>
    </#list>
</div>
