package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;
import ru.mvc.annotation.Column;
import ru.mvc.annotation.Table;

@Getter
@Setter

//@Table(name = "event")
public class Event {
//    @Column(name = "id")
    private int id;
//    @Column(name = "user_id")
    private User user;
//    @Column(name = "name")
    private String name;
//    @Column(name = "city")
    private String city;
//    @Column(name = "street")
    private String street;
//    @Column(name = "house")
    private String house;
//    @Column(name = "date")
    private String date;
//    @Column(name = "image")
    private Image image;
//    @Column(name = "description")
    private String description;
//    @Column(name = "category_id")
    private Categories category;
//    @Column(name = "time")
    private String time;
//    @Column(name = "status")
    private String status;

    public Event() {
    }

}
