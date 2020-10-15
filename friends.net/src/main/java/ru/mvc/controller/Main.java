package ru.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        java.util.Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(formatForDateNow.format(dateNow));

        System.out.println(formatForDateNow.format(dateNow));

    }
}
