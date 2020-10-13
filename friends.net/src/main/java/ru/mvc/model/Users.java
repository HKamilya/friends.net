package ru.mvc.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Users {

    private int id;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private String image;
    private String description;

    public Users() {

    }
}