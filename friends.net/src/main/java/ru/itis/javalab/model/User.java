package ru.itis.javalab.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "account")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;


    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    private String description;
    private String UUID;
    private State state;
    private String confirmCode;
    private Role role;


    @ToString.Exclude
    @OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY)
    private List<Request> requests;

    @ToString.Exclude
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Event> events;


    public enum State {
        NOT_CONFIRMED, CONFIRMED
    }

    public enum Role {
        ADMIN, USER
    }
}