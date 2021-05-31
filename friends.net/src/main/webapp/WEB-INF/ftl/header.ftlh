<#import  "spring.ftl" as spring/>
<#macro main css=[]>
    <html>
    <head>
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Ravi+Prakash&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
              integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
              crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript"
                src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>


        <meta charset="UTF-8">
        <link rel="stylesheet" href="/static/css/style_menu.css">
        <link rel="stylesheet" href="/static/css/style.css">
    </head>
    <body>
    <header class="header">
        <div class="container_main">
            <div class="header__inner">
                <div class="hl">Friends.net</div>
                <nav class="nav">
                    <#if auth??>
                        <a class="nav__link" href="/addEvent"><@spring.message 'header_page.nav.create'/>
                            <br>
                            <@spring.message 'header_page.nav.event'/></a>
                    </#if>
                    <a class="nav__link" href="/search"><@spring.message'header_page.nav.search'/></a>
                    <div class="header__logo">
                        <img class="picture" src="/static/img/man.png" alt="">
                    </div>
                    <a class="nav__link" href="/allEvents"><@spring.message 'header_page.nav.interest'/>
                        <br><@spring.message 'header_page.nav.events'/></a>
                    <a class="nav__link" href="/randomEvent"><@spring.message 'header_page.nav.random'/><br>
                        <@spring.message 'header_page.nav.event'/></a>
                </nav>
                <div class="header__auth">

                    <#if !auth??>
                        <a class="auth__link"
                           href="/signUp"><@spring.message 'sign_up_page.registration.title'/></a>
                        <a class="auth__link" href="/signIn"><@spring.message 'header_page.registration.logIn'/></a>
                    <#else >
                        <a class="auth__link" href="/profile">${auth}</a>
                        <a href="/logout"><@spring.message 'header_page.registration.logOut'/></a>
                    </#if>
                    <a class="auth__link" href="?lang=ru">РУ</a>
                    <a class="auth__link" href="?lang=en">EN</a>

                </div>
            </div>
        </div>
    </header>
    <#nested >
    </body>
    </html>
</#macro>