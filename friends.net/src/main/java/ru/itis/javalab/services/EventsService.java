package ru.itis.javalab.services;

import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventsService {

    public List<Event> findByUserId(Long id);

    public void save(EventDto event, Long id);

    public Optional<Event> findById(Long id);

    public List<Event> findByPage(int page, int size);

    void update(Event event);

    public List<Event> findAll();

    public List<Event> findAllByStatus();

    public Event findRandom();

    List<Event> search(String name, List<Long> s, String date);
}

