package ru.mvc;

import ru.kpfu.itis.orm.service.DBCreator;

public class Main
{
    public static void main(String[] args) {
        DBCreator dbCreator = new DBCreator();
        dbCreator.create("ru.mvc.model");
    }
}
