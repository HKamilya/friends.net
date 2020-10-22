package ru.mvc.model;

import lombok.Getter;
import lombok.Setter;
import ru.mvc.model.Event;
import ru.mvc.model.User;

@Getter
@Setter


public class Request {
    private Event event;
    private User subscriber;
    private String comment;

    public Request() {
    }
}
