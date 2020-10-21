package ru.mvc.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private int id;
    private String type;
    private String address;

    public Image() {

    }
}
