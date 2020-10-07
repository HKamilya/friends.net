package ru.mvc.bean;

public class Request {
    private int event_id;
    private int subscriber;
    private String comment;

    public Request() {

    }

    public Request(int event_id, int subscriber, String comment) {
        this.event_id = event_id;
        this.subscriber = subscriber;
        this.comment = comment;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(int subscriber) {
        this.subscriber = subscriber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
