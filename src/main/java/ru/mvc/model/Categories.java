package ru.mvc.model;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;

@Getter
@Setter

@Table(name = "categories")
public class Categories {
    @Constraint(pk = true)
    private Integer id;
    @Constraint(notNull = true)
    private String name;
    private String description;

    public Categories() {

    }

}