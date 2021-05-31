package ru.itis.javalab.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User creator;
    private String name;
    private String city;
    private String street;
    private String house;
    private Date date;
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    private String description;
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")

    private Category category;
    private String status;

    @ToString.Exclude
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Request> requests;

    @ToString.Exclude
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
