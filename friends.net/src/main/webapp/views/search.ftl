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
    <script>
        $(function () {
            $('#button').click(function (event) {
                var search = $('#search').val();
                console.log(search);
                var tags = [];
                var i = 0;
                $('input:checkbox:checked').each(function () {
                    tags[i] = $(this).val();
                    i++;
                });
                var date = $('#date').val();
                console.log(tags);
                $.ajax({
                    type: "POST", url: "Servlets.SearchServlet",
                    data: {
                        search: search,
                        tags: JSON.stringify(tags),
                        date: date
                    },
                    dataType: 'json',
                    success: function (result) {
                    }
                });
            });
        });
    </script>
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

        .autocomplete {
            /*the container must be positioned relative:*/
            position: relative;
            display: inline-block;
        }

        input {
            border: 1px solid transparent;
            padding: 10px;
            font-size: 16px;
        }

        input[type=text] {
            background-color: #f1f1f1;
            width: 100%;
        }

        input[type=submit] {
            background-color: gray;
            color: #fff;
            cursor: pointer;
        }

        .autocomplete-items {
            position: absolute;
            border: 1px solid #d4d4d4;
            border-bottom: none;
            border-top: none;
            z-index: 99;
            /*position the autocomplete items to be the same width as the container:*/
            top: 100%;
            left: 0;
            right: 0;
        }

        .autocomplete-items div {
            padding: 10px;
            cursor: pointer;
            background-color: #fff;
            border-bottom: 1px solid #d4d4d4;
        }

        .autocomplete-items div:hover {
            /*when hovering an item:*/
            background-color: #e9e9e9;
        }

        .autocomplete-active {
            /*when navigating through the items using the arrow keys:*/
            background-color: DodgerBlue !important;
            color: #ffffff;
        }
    </style>


    <div class="main-content"><p class="headline"></p>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"><p><b>Фильтры</b></p>
                    <form id="filter" method="post" autocomplete="off" action="/Search">
                        <div class="autocomplete">
                            <input class="search" accept="" name="search" id="search"
                                   placeholder="Поиск по названию..." type="text">
                        </div>
                        <br>
                        <table>
                            <th>По категории:</th>
                            <input type="hidden" name="category" value="0">
                            <br>
                            <#list catList as catList>
                                <tr>
                                    <td>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="category"
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
                                        <input type="date" name="date" id="date">
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
    <script>
        function autocomplete(inp, arr) {
            /*the autocomplete function takes two arguments,
            the text field element and an array of possible autocompleted values:*/
            var currentFocus;
            /*execute a function when someone writes in the text field:*/
            inp.addEventListener("input", function (e) {
                var a, b, i, val = this.value;
                /*close any already open lists of autocompleted values*/
                closeAllLists();
                if (!val) {
                    return false;
                }
                currentFocus = -1;
                /*create a DIV element that will contain the items (values):*/
                a = document.createElement("DIV");
                a.setAttribute("id", this.id + "autocomplete-list");
                a.setAttribute("class", "autocomplete-items");
                /*append the DIV element as a child of the autocomplete container:*/
                this.parentNode.appendChild(a);
                /*for each item in the array...*/
                for (i = 0; i < arr.length; i++) {
                    /*check if the item starts with the same letters as the text field value:*/
                    if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                        /*create a DIV element for each matching element:*/
                        b = document.createElement("DIV");
                        /*make the matching letters bold:*/
                        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                        b.innerHTML += arr[i].substr(val.length);
                        /*insert a input field that will hold the current array item's value:*/
                        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                        /*execute a function when someone clicks on the item value (DIV element):*/
                        b.addEventListener("click", function (e) {
                            /*insert the value for the autocomplete text field:*/
                            inp.value = this.getElementsByTagName("input")[0].value;
                            /*close the list of autocompleted values,
                            (or any other open lists of autocompleted values:*/
                            closeAllLists();
                        });
                        a.appendChild(b);
                    }
                }
            });
            /*execute a function presses a key on the keyboard:*/
            inp.addEventListener("keydown", function (e) {
                var x = document.getElementById(this.id + "autocomplete-list");
                if (x) x = x.getElementsByTagName("div");
                if (e.keyCode == 40) {
                    /*If the arrow DOWN key is pressed,
                    increase the currentFocus variable:*/
                    currentFocus++;
                    /*and and make the current item more visible:*/
                    addActive(x);
                } else if (e.keyCode == 38) { //up
                    /*If the arrow UP key is pressed,
                    decrease the currentFocus variable:*/
                    currentFocus--;
                    /*and and make the current item more visible:*/
                    addActive(x);
                } else if (e.keyCode == 13) {
                    /*If the ENTER key is pressed, prevent the form from being submitted,*/
                    e.preventDefault();
                    if (currentFocus > -1) {
                        /*and simulate a click on the "active" item:*/
                        if (x) x[currentFocus].click();
                    }
                }
            });

            function addActive(x) {
                /*a function to classify an item as "active":*/
                if (!x) return false;
                /*start by removing the "active" class on all items:*/
                removeActive(x);
                if (currentFocus >= x.length) currentFocus = 0;
                if (currentFocus < 0) currentFocus = (x.length - 1);
                /*add class "autocomplete-active":*/
                x[currentFocus].classList.add("autocomplete-active");
            }

            function removeActive(x) {
                /*a function to remove the "active" class from all autocomplete items:*/
                for (var i = 0; i < x.length; i++) {
                    x[i].classList.remove("autocomplete-active");
                }
            }

            function closeAllLists(elmnt) {
                /*close all autocomplete lists in the document,
                except the one passed as an argument:*/
                var x = document.getElementsByClassName("autocomplete-items");
                for (var i = 0; i < x.length; i++) {
                    if (elmnt != x[i] && elmnt != inp) {
                        x[i].parentNode.removeChild(x[i]);
                    }
                }
            }

            /*execute a function when someone clicks in the document:*/
            document.addEventListener("click", function (e) {
                closeAllLists(e.target);
            });
        }

        /*An array containing all the country names in the world:*/
        var countries = ["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla"];
        /*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
        autocomplete(document.getElementById("search"), countries);
    </script>

</@base.main>