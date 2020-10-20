package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Event {
    private int id;
    private User user;
    private String name;
    private String city;
    private String street;
    private String house;
    private String date;
    private String image;
    private String description;
    private Categories category;
    private String time;
    private String status;

    public Event() {
    }

}
