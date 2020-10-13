package ru.mvc.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Events {
    private int id;
    private Users user;
    private String name;
    private String city;
    private String street;
    private String house;
    private String date;
    private String image;
    private String description;
    private Categories category;
    private String status;

    public Events(){}

}
