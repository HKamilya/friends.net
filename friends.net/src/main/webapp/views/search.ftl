<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<link rel="stylesheet" href="../css/style_menu_alternative.css">
<@base.main>

    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ravi+Prakash&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../css/style_menu_alternative.css">
    <title>List</title>
    <script type="text/javascript">
        $(document).ready(function () {

            $('#search').autocomplete({
                source: '/ajax'
            });

        });
    </script>
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


    <div class="main-content"><p class="headline"></p>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"><p><b>Фильтры</b></p>
                    <form id="filter" method="post" action="/Search">
                        <input class="search" autocomplete="off" name="search" id="search" placeholder="Поиск по названию..." type="text">
                        <br>
                        <table>
                            <th>По категории:</th>
                            <input type="hidden" name="category" value="0">
                            <br>
                            <#list catList as catList>
                                <tr>
                                    <td>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="outside"
                                                   value="${catList.id}" name="category">
                                            <label class="form-check-label" for="outside">${catList.name}</label>
                                        </div>
                                    </td>
                                </tr>
                            </#list>

                            <th>По дате:</th>
                            <tr>
                                <td>
                                    <label>
                                        <input type="date" name="date">
                                    </label>
                                </td>
                            </tr>
                        </table>
                        <input class="sub" type="submit" value="Поиск" style="margin-bottom: 5px">
                    </form>
                </div>
                <div class="col-sm-8" style="border-left: 1px solid black">

                    <div class="main">
                        <#include "allEv.ftl">
                        <#if message??>
                            <h2>${message}</h2>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.main>