package ru.mvc.model;

public class Review {
    private Events event;
    private Users user;
    private String text;

    public Review() {
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
