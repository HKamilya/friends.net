package ru.mvc.bean;

public class Review {
    private int event_id;
    private int user_id;
    private String text;

    public Review() {
    }

    public Review(int event_id, int user_id, String text) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.text = text;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
