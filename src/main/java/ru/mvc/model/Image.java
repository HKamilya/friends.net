package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.orm.annotation.Constraint;
import ru.kpfu.itis.orm.annotation.Table;


@Getter
@Setter
@Table(name = "image")
public class Image {
    @Constraint(pk = true)
    private Integer id;
    @Constraint(notNull = true)
    private String type;
    @Constraint(notNull = true)
    private String address;

    public Image() {

    }
}
