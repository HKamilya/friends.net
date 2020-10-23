package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;


@Getter
@Setter

//@Table(name = "review")
public class Review {
//    @Column(name = "event_id")
    private Event event;
//    @Column(name = "user_id")
    private User user;
//    @Column(name = "text")
    private String text;
//    @Column(name = "date")
    private String date;

    public Review() {
    }
}
