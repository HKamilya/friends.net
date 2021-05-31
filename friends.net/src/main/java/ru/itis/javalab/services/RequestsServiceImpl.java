package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.RequestDto;
import ru.itis.javalab.model.Request;
import ru.itis.javalab.repositories.RequestsRepository;

import java.util.List;

@Service
public class RequestsServiceImpl implements RequestsService {
    @Autowired
    private RequestsRepository requestsRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private EventsService eventsService;

    public RequestsServiceImpl(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    @Override
    public List<Request> findAllByEventId(Long id) {
        return requestsRepository.findAllByEvent_Id(id);
    }

    public List<Request> findAllByUserId(Long id) {
        return requestsRepository.findAllBySubscriber_Id(id);
    }

    @Override
    public void delete(Long eventId, Long subscriberId) {
        requestsRepository.deleteRequestByEvent_IdAndAndSubscriber_Id(eventId, subscriberId);
    }

    @Override
    public void insert(RequestDto adr) {
        Request request = Request.builder()
                .comment(adr.getComment())
                .subscriber(usersService.findById(adr.getUserId()).get())
                .event(eventsService.findById(adr.getEventId()).get())
                .build();
        requestsRepository.save(request);
    }
}
