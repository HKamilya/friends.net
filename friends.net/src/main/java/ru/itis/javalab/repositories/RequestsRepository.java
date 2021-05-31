package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.Request;

import java.util.List;
import java.util.Optional;


public interface RequestsRepository extends JpaRepository<Request, Long> {


    List<Request> findAllByEvent_Id(Long id);

    List<Request> findAllBySubscriber_Id(Long id);

    void deleteRequestByEvent_IdAndAndSubscriber_Id(Long eventId, Long subscriberId);
}
