package ru.itis.javalab.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.model.User;

import javax.mail.Multipart;
import javax.servlet.http.Part;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class EventDto {
    private String name;
    private String city;
    private String street;
    private String house;
    private String date;
    private String time;
    private MultipartFile filePart;
    private String description;
    private Long category_id;
}
