<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>
    <#list list as event>
        <div class="main">
            <a class="name" href="#">${event.name}</a>
            <div class="event">
                <div class="h_p">
                    <div class="img">
                        <img class="photo" src="img/bfr.jpg">
                    </div>
                </div>
                <div class="descr">
                    ${event.description}
                </div>
                <form method="get" action="/ListOfRequests">
                    <input type="hidden" value="${event.id}" name="event_id">
                    <button type="submit" class="btn btn-primary" data-toggle="modal"
                            data-target="#exampleModalLong">
                        Список идущих
                    </button>
                </form>
            </div>
        </div>
    </#list>


    <div class="modal fade" id="piknik" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="piknikTitle">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <#list reqList as req>
                        <a href="/AnProfile?username=${req.subscriber.username}">${req.subscriber.username}</a>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</@base.main>