package ru.mvc.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Request {
    private Events event;
    private Users subscriber;
    private String comment;

    public Request() {
    }
}
