package ru.itis.javalab.services;

import ru.itis.javalab.dto.RequestDto;
import ru.itis.javalab.model.Request;

import java.util.List;

public interface RequestsService {
    public List<Request> findAllByEventId(Long id);


    void delete(Long eventId, Long subscriberId);

    public void insert(RequestDto adr);


}
