package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter
//
@Table(name = "supportMessage")
public class SupportMessage {
    @Constraint( pk = true)
    private Integer id;
    @Constraint(notNull = true)
    private String email;
    @Constraint(notNull = true)
    private String title;
    @Constraint(notNull = true)
    private String text;
    @Constraint(notNull = true)
    private String date;

    public SupportMessage() {
    }
}
