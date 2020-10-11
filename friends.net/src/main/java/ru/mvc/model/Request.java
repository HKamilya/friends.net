package ru.mvc.model;

import java.awt.*;

public class Request {
    private Events event;
    private Users subscriber;
    private String comment;

    public Request() {

    }

    public Request(Events event,Users subscriber, String comment) {
        this.event = event;
        this.subscriber = subscriber;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Users getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Users subscriber) {
        this.subscriber = subscriber;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
