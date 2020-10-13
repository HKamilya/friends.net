package ru.mvc.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Categories {
    private int id;
    private String name;
    private String description;

    public Categories(){

    }

}