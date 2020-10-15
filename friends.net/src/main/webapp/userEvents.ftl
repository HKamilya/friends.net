<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>
    <style>
        .photo {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }

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
    </style>
    <#list evReqList as event, request>

        <div class="main">
            <a class="name" href="/Event?id=${event.id}">${event.name}</a>
            <div class="event">
                <div class="h_p">
                    <div class="img">
                        <img class="photo" src="img/bfr.jpg">
                    </div>
                </div>
                <div class="descr">
                    ${event.description}
                </div>
                <div class="descr">-->
                    <p>${event.status}</p>
                </div>

                    <form action="/Profile" method="post">
                        <input type="hidden" name="event_id" value="${event.id}">
                        <select name="status">
                            <option name="status_name" value="1">актуально</option>
                            <option name="status_name" value="2">неактуально</option>
                        </select>
                        <button type="submit" name="submit">Сохранить</button>
                    </form>

                <button type="submit" class="btn btn-primary" data-toggle="modal"
                        data-target="#event${event.id}">
                    Список идущих
                </button>
            </div>
        </div>
        <div class="modal fade" id="event${event.id}" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLongTitle"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <#list request as req>
                            <a href="/AnProfile?username=${req.subscriber.userName}">${req.subscriber.fullName}</a>
                            <br>
                            <#if req.comment??>
                                <p>${req.comment}</p>
                            </#if>

                        </#list>

                    </div>
                </div>
            </div>
        </div>
    </#list>
</@base.main>