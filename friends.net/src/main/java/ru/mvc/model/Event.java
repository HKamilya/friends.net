package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter

@Table(name = "event")
public class Event {
    @Constraint(pk = true)
    private Integer id;
    @Constraint(notNull = true)
    private User user_id;
    @Constraint(notNull = true)
    private String name;
    @Constraint(notNull = true)
    private String city;
    private String street;
    private String house;
    @Constraint(notNull = true)
    private String date;
    @Constraint(notNull = true)
    private Image image;
    @Constraint(notNull = true)
    private String description;
    @Constraint(notNull = true)
    private Categories category_id;
    @Constraint(notNull = true)
    private String time;
    @Constraint(notNull = true)
    private String status;

    public Event() {
    }

}
