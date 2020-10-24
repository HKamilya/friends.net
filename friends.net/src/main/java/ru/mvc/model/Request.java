package ru.mvc.model;

import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;
import ru.mvc.model.Event;
import ru.mvc.model.User;

@Getter
@Setter

//@Table(name = "request")
public class Request {
//    @Column(name = "event_id")
    private Event event_id;
//    @Column(name = "subscriber_id")
    private User subscriber_id;
//    @Column(name = "comment")
    private String comment;

    public Request() {
    }
}
