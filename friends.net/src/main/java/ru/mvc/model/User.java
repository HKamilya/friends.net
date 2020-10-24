package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;


@Getter
@Setter

public class User {

    private int id;
    private String fullname;
    private String email;
    private String username;
    private String password;
    private Image image;
    private String description;

    public User() {

    }
}