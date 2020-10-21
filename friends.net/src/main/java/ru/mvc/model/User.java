package ru.mvc.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

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