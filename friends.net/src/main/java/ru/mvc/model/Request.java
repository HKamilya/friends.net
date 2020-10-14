package ru.mvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Request {
    private Event event;
    private User subscriber;
    private String comment;

    public Request() {
    }
}
