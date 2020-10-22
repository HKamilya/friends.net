package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupportMessage {
    private String email;
    private String title;
    private String text;

    public SupportMessage() {
    }
}
