package ru.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathName = "src\\main\\webapp\\img\\" + "ghj.jpg";
        File file = new File(pathName);
        boolean created = file.createNewFile();

    }
}
