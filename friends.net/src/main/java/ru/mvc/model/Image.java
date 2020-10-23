package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;

@Getter
@Setter
//@Table(name = "image")
public class Image {
//    @Column(name = "id")
    private int id;
//    @Column(name = "type")
    private String type;
//    @Column(name = "address")
    private String address;

    public Image() {

    }
}
