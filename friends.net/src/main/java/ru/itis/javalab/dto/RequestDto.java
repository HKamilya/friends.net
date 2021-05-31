package ru.itis.javalab.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto {
    String comment;
    Long userId;
    Long eventId;

}
