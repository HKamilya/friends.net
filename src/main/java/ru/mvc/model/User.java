package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter

@Table(name = "user")
public class User {
    @Constraint(pk = true)
    private Integer id;
    @Constraint(notNull = true)
    private String fullname;
    @Constraint(notNull = true)
    private String email;
    @Constraint(notNull = true)
    private String username;
    @Constraint(notNull = true)
    private String password;
    @Constraint(notNull = true)
    private Image image;
    private String description;

    public User() {

    }
}