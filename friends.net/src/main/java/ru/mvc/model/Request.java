package ru.mvc.model;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter

@Table(name = "request")
public class Request {
    @Constraint(notNull = true)
    private Event event_id;
    @Constraint(notNull = true)
    private User subscriber_id;
    private String comment;

    public Request() {
    }
}
