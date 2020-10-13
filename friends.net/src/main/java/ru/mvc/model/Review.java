package ru.mvc.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Review {
    private Events event;
    private Users user;
    private String text;

    public Review(){}
}
