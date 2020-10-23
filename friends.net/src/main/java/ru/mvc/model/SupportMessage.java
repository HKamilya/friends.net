package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;

@Getter
@Setter
//
//@Table(name = "supportMessage")
public class SupportMessage {
//    @Column(name = "id")
    private int id;
//    @Column(name = "email")
    private String email;
//    @Column(name = "title")
    private String title;
//    @Column(name = "text")
    private String text;

    public SupportMessage() {
    }
}
