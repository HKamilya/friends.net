package ru.itis.javalab.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String username;

    private Image image;
    private String description;
    private String state;
    private String role;
}
