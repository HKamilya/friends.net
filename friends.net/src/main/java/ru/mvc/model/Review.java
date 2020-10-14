package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Review {
    private Event event;
    private User user;
    private String text;

    public Review(){}
}
