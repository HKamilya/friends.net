package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class User {

    private int id;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private Image image;
    private String description;

    public User() {

    }
}