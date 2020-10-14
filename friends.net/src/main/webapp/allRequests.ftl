<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Ravi+Prakash&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script type="text/javascript"
                src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="style_menu_alternative.css">
        <title>List</title>
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
    </head>
    <body>
    <header class="header">
        <div class="container_main">
            <div class="header__inner">
                <div class="hl">Friends.net</div>

                <nav class="nav">
                    <a class="nav__link" href="#">Организовать <br> Мероприятие</a>
                    <a class="nav__link" href="#">Поиск</a>
                    <div class="header__logo">
                        <img class="picture" src="img/man.png" alt="">
                    </div>
                    <a class="nav__link" href="#">Интересные<br>мероприятия</a>
                    <a class="nav__link" href="#">Случайное<br>мероприятие</a>
                </nav>

                <div class="header__auth">
                    <a class="auth__link" href="#">Регистрация</a>
                    <a class="auth__link" href="#">Вход</a>
                </div>
            </div>
        </div>
    </header>

    <div class="main-content"><p class="headline"></p>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"><p><b>Фильтры</b></p>
                    <form id="filter">
                        <table>
                            <th>По категории:</th>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="outside" value="outside">
                                        <label class="form-check-label" for="outside">Уличные</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="music" value="music">
                                        <label class="form-check-label" for="music">Музыка</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="art" value="art">
                                        <label class="form-check-label" for="art">Исскуство</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="new places"
                                               value="new place">
                                        <label class="form-check-label" for="new places">Новые места</label>
                                    </div>
                                </td>
                            </tr>
                            <th>По времени:</th>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="early" value="early">
                                        <label class="form-check-label" for="early">Раннее утро</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="middle" value="middle">
                                        <label class="form-check-label" for="middle">Дневное время</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="evening" value="evening">
                                        <label class="form-check-label" for="evening">Вечернее</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <input class="sub" type="submit" value="Принять" style="margin-bottom: 5px"></form>
                </div>
                <div class="col-sm-8" style="border-left: 1px solid black"><input class="search"
                                                                                  placeholder="Поиск по названию..."
                                                                                  type="search">

                    <div class="main">
                        <a class="name" href="#">Пикник в парке</a>
                        <div class="event">
                            <div class="h_p">
                                <div class="img">
                                    <img class="photo" src="img/pivo.jpg">
                                </div>
                            </div>
                            <div class="descr">
                                <p>Все жарче припекает весеннее солнышко, температура на термометре стремительно растет,
                                    и
                                    совсем скоро весь
                                    город утонет в нежно-зеленой листве деревьев. Все говорит о том, что наступает пора
                                    пикников.</p>
                                <p>В Великобритании с началом весенне-летнего периода привычный five o'clock
                                    преображается в
                                    обильную
                                    трапезу под открытым небом. Для почитателей пикников созданы специальные магазины,
                                    где
                                    продается
                                    всевозможный инвентарь и аксессуары, также существуют сайты с адресами и описаниями
                                    оборудованных
                                    площадок. Известная газета The Guardian в начале каждого сезона составляет и
                                    публикует
                                    рейтинги лучших
                                    мест для пикников.</p>
                                <p>Именно на такой пикник мы вас приглашаем.</p>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModalLong">
                                Список идущих
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
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
                    <a href="#">Kamilya</a>
                </div>
            </div>
        </div>
    </div>

    </body>
    </html>
</@base.main>