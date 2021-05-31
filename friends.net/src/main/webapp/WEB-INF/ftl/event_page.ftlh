<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>

    <link rel="stylesheet" href="/static/css/event.css">
    <#if event??>
        <div class="event_profile">
            <div class="row">
                <div class="col-5">
                    <div class="image">
                        <img class="picture" src="/img?id=${event.image.id}" alt="">
                    </div>
                </div>
                <div class="col-7">
                    <div class="text_block">
                        <div class="name_attend">
                            <div class="name">
                                ${event.name}
                            </div>
                            <div class="attend">
                                <@spring.message 'event_page.event_description.count'/>
                                <br>
                                <#if requests??>
                                    ${requests.size()}
                                </#if>
                            </div>
                        </div>
                        <div class="where_when">
                            <div class="where">
                                <p>${event.city}
                                    <br>
                                    <#if event.street??> ${event.street}</#if>
                                    <#if event.house??> ${event.house}</#if>
                                </p></div>
                            <div class="when">
                                <p>
                                    ${event.date}</p>
                            </div>
                        </div>
                        <div class="inform">
                            <p>${event.description}</p>
                        </div>
                        <div class="inform">
                            <p>${event.category.name}</p>
                        </div>
                        <div class="inform">
                            <a href="/anProfile?username=${event.creator.username}">${event.creator.username}</a>
                        </div>
                        <div class="tags">
                        </div>
                    </div>
                    <div class="but" style="text-align: center">
                        <#if time??>
                            <button type="button" name="submit" data-toggle="modal"
                                    data-target="#requestModal"><@spring.message 'event_page.request.will'/>
                            </button>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="requestModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="text-align: center">
                        <#if user??>
                            <form name="form" action="sendRequest" method="post" onsubmit="return validate()">
                                <label style="font-size: 20px"><@spring.message 'event_page.request.comment'/></label>
                                <br>
                                <textarea style="width: 200px;" type="text" name="comment"></textarea>
                                <input type="hidden" name="event_id" value="${event.id}">
                                <#if errMessage??>
                                    ${errMessage}
                                </#if>
                                <br>
                                <br>
                                <input type="submit" class="btn btn-dark"
                                       value="<@spring.message 'event_page.request.send'/>"/><input
                                        class="btn btn-dark" type="reset" value="add_event_page.form.reset"/>
                            </form>
                        <#else >

                            <label style="font-size: 15px">
                                <@spring.message 'event_page.event_description.request_unfor'/>
                                <br>
                                <@spring.message 'event_page.event_description.request_unfor_2'/>
                            </label>
                            <input type="submit" class="btn btn-dark" onclick="location.href='/signIn'"
                                   value="<@spring.message 'header_page.registration.logIn'/>"/>
                            <input
                                    class="btn btn-dark" type="reset" onclick="location.href='/signUp'"
                                    value="<@spring.message 'sign_up_page.registration.title'/>"/>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <section class="container">
            <div class="row">1
                <div class="col-md-12">
                    <div class="panel">
                        <div class="panel-body">
                            <#if user??>
                                <form action="addReview" method="post" onsubmit="return validate()">
                        <textarea class="form-control" rows="2"
                                  placeholder="<@spring.message 'event_page.add_comment'/>"
                                  name="review"></textarea>
                                    <div class="mar-top clearfix">
                                        <input type="hidden" name="event_id" value="${event.id}">
                                        <#if errMessage??>
                                            ${errMessage}
                                        </#if>
                                        <button class="btn btn-sm btn-primary pull-right" type="submit"><i
                                                    class="fa fa-pencil fa-fw"></i> <@spring.message 'add_event_page.form.add'/>
                                        </button>
                                        <a class="btn btn-trans btn-icon fa fa-video-camera add-tooltip"
                                           href="#"></a>
                                        <a class="btn btn-trans btn-icon fa fa-camera add-tooltip" href="#"></a>
                                        <a class="btn btn-trans btn-icon fa fa-file add-tooltip" href="#"></a>
                                    </div>
                                </form>
                            </#if>
                        </div>
                    </div>
                    <div class="panel">
                        <div class="panel-body">
                            <#if reviews??>
                                <ul><#list reviews as review>
                                        <div class="media-block">
                                            <a class="media-left" href="#"><img class="img-circle img-sm"
                                                                                alt="Профиль пользователя"
                                                                                src="/img?id=${review.user.image.id}"></a>
                                            <div class="media-body">
                                                <div class="mar-btm">
                                                    <div class="mar-btm">
                                                        <a href="/AnProfile?username=${review.user.username}"
                                                           class="btn-link text-semibold media-heading box-inline">${review.user_id.fullname}</a>
                                                        <p class="text-muted text-sm"><i
                                                                    class="fa fa-mobile fa-lg"></i>
                                                            -
                                                            ${review.date}</p>
                                                    </div>
                                                </div>
                                                <p>${review.text}</p>
                                                <div class="pad-ver">
                                                    <div class="btn-group">
                                                        <a class="btn btn-sm btn-default btn-hover-success"
                                                           href="#"><i
                                                                    class="fa fa-thumbs-up"></i></a>
                                                        <a class="btn btn-sm btn-default btn-hover-danger" href="#"><i
                                                                    class="fa fa-thumbs-down"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </ul>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </#if>
</@base.main>
