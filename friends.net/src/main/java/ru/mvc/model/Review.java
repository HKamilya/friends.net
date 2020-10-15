package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class Review {
    private Event event;
    private User user;
    private String text;
    private String date;

    public Review() {
    }
}
