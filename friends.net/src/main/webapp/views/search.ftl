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
    <link rel="stylesheet" href="../css/search.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>

        $(function () {
            $('#button').click(function (event) {
                var search = $('#search').val();
                console.log(search);
                // var elements = document.forms['filter'].elements['tags'];
                // var len = elements.length;
                var checkboxes = document.getElementsByName('cat');
                var tags = [];
                i = 0;
                for (var index = 0; index < checkboxes.length; index++) {
                    if (checkboxes[index].checked) {
                        tags[i] = checkboxes[index].value;
                        i++;
                    }
                }
                var date = $('#date').val();

                $.ajax({
                    type: "POST", url: "/Search",
                    data: {
                        search: search,
                        tags: JSON.stringify(tags), // все чекбоксы в массиве
                        date: date
                    },
                    dataType: 'json',
                    success: function (result) {
                        $("#vstavka").detach();
                        $("<div id=\"vstavka\">").appendTo($("#v"));
                        for (var i = 0; i < result.length; i++) {
                            $('#vstavka').append($(' <a class=\"name\" href="/Event?id=' + result[i]['id'] + '">' + result[i]['name'] + '</a>' +
                                '<div class="event">' +
                                '  <div class="h_p">' +
                                '<div class="img">' +
                                ' <img  alt="" class="photo" src="/img?id=' + result[i]['image']['id'] + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="descr"><p>' + result[i]['description'] +
                                '</p>  <br><p>' + result[i]['date'] + '</p>' +
                                '           <br> <p>' + result[i]['time'] + '</p> <br><p>' + result[i]['category_id']['name'] + '</p>' +
                                '</div></div></div>'
                            ))
                        }
                    }
                });
            });
        });
    </script>
    <title>List</title>

    <div class="main-content"><p class="headline"></p>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"><p><b>Фильтры</b></p>
                    <form id="filter" name="filter" autocomplete="off">
                        <div class="autocomplete">
                            <input class="search" accept="" name="search" id="search"
                                   placeholder="Поиск по названию..." type="text">
                        </div>
                        <br>
                        <table>
                            <th>По категории:</th>
                            <br>
                            <#list catList as catList>
                                <tr>
                                    <td>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" name="cat" id="category"
                                                   value="${catList.id}">
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
                        <input class="sub" id="button" type="button" value="Поиск" style="margin-bottom: 5px">
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
<#--    <script>-->
<#--        function autocomplete(inp, arr) {-->
<#--            /*the autocomplete function takes two arguments,-->
<#--            the text field element and an array of possible autocompleted values:*/-->
<#--            var currentFocus;-->
<#--            /*execute a function when someone writes in the text field:*/-->
<#--            inp.addEventListener("input", function (e) {-->
<#--                var a, b, i, val = this.value;-->
<#--                /*close any already open lists of autocompleted values*/-->
<#--                closeAllLists();-->
<#--                if (!val) {-->
<#--                    return false;-->
<#--                }-->
<#--                currentFocus = -1;-->
<#--                /*create a DIV element that will contain the items (values):*/-->
<#--                a = document.createElement("DIV");-->
<#--                a.setAttribute("id", this.id + "autocomplete-list");-->
<#--                a.setAttribute("class", "autocomplete-items");-->
<#--                /*append the DIV element as a child of the autocomplete container:*/-->
<#--                this.parentNode.appendChild(a);-->
<#--                /*for each item in the array...*/-->
<#--                for (i = 0; i < arr.length; i++) {-->
<#--                    /*check if the item starts with the same letters as the text field value:*/-->
<#--                    if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {-->
<#--                        /*create a DIV element for each matching element:*/-->
<#--                        b = document.createElement("DIV");-->
<#--                        /*make the matching letters bold:*/-->
<#--                        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";-->
<#--                        b.innerHTML += arr[i].substr(val.length);-->
<#--                        /*insert a input field that will hold the current array item's value:*/-->
<#--                        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";-->
<#--                        /*execute a function when someone clicks on the item value (DIV element):*/-->
<#--                        b.addEventListener("click", function (e) {-->
<#--                            /*insert the value for the autocomplete text field:*/-->
<#--                            inp.value = this.getElementsByTagName("input")[0].value;-->
<#--                            /*close the list of autocompleted values,-->
<#--                            (or any other open lists of autocompleted values:*/-->
<#--                            closeAllLists();-->
<#--                        });-->
<#--                        a.appendChild(b);-->
<#--                    }-->
<#--                }-->
<#--            });-->
<#--            /*execute a function presses a key on the keyboard:*/-->
<#--            inp.addEventListener("keydown", function (e) {-->
<#--                var x = document.getElementById(this.id + "autocomplete-list");-->
<#--                if (x) x = x.getElementsByTagName("div");-->
<#--                if (e.keyCode == 40) {-->
<#--                    /*If the arrow DOWN key is pressed,-->
<#--                    increase the currentFocus variable:*/-->
<#--                    currentFocus++;-->
<#--                    /*and and make the current item more visible:*/-->
<#--                    addActive(x);-->
<#--                } else if (e.keyCode == 38) { //up-->
<#--                    /*If the arrow UP key is pressed,-->
<#--                    decrease the currentFocus variable:*/-->
<#--                    currentFocus--;-->
<#--                    /*and and make the current item more visible:*/-->
<#--                    addActive(x);-->
<#--                } else if (e.keyCode == 13) {-->
<#--                    /*If the ENTER key is pressed, prevent the form from being submitted,*/-->
<#--                    e.preventDefault();-->
<#--                    if (currentFocus > -1) {-->
<#--                        /*and simulate a click on the "active" item:*/-->
<#--                        if (x) x[currentFocus].click();-->
<#--                    }-->
<#--                }-->
<#--            });-->

<#--            function addActive(x) {-->
<#--                /*a function to classify an item as "active":*/-->
<#--                if (!x) return false;-->
<#--                /*start by removing the "active" class on all items:*/-->
<#--                removeActive(x);-->
<#--                if (currentFocus >= x.length) currentFocus = 0;-->
<#--                if (currentFocus < 0) currentFocus = (x.length - 1);-->
<#--                /*add class "autocomplete-active":*/-->
<#--                x[currentFocus].classList.add("autocomplete-active");-->
<#--            }-->

<#--            function removeActive(x) {-->
<#--                /*a function to remove the "active" class from all autocomplete items:*/-->
<#--                for (var i = 0; i < x.length; i++) {-->
<#--                    x[i].classList.remove("autocomplete-active");-->
<#--                }-->
<#--            }-->

<#--            function closeAllLists(elmnt) {-->
<#--                /*close all autocomplete lists in the document,-->
<#--                except the one passed as an argument:*/-->
<#--                var x = document.getElementsByClassName("autocomplete-items");-->
<#--                for (var i = 0; i < x.length; i++) {-->
<#--                    if (elmnt != x[i] && elmnt != inp) {-->
<#--                        x[i].parentNode.removeChild(x[i]);-->
<#--                    }-->
<#--                }-->
<#--            }-->

<#--            /*execute a function when someone clicks in the document:*/-->
<#--            document.addEventListener("click", function (e) {-->
<#--                closeAllLists(e.target);-->
<#--            });-->
<#--        }-->

<#--        /*An array containing all the country names in the world:*/-->
<#--        var countries = ["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla"];-->
<#--        /*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/-->
<#--        autocomplete(document.getElementById("search"), countries);-->
<#--    </script>-->

</@base.main>