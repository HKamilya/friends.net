package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter

@Table(name = "review")
public class Review {
    @Constraint(notNull = true)
    private Event event_id;
    @Constraint(notNull = true)
    private User user_id;
    @Constraint(notNull = true)
    private String text;
    @Constraint(notNull = true)
    private String date;

    public Review() {
    }
}
