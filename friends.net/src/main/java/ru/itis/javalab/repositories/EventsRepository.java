package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.model.Category;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.User;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


public interface EventsRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {


    List<Event> findAllByCreatorId(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM event ev where ev.name ILIKE ? and ev.status='актуально'")
    List<Event> findAllByName(String name);

    @Query(nativeQuery = true, value = "select * from event order by random() limit 1")
    Event findByRandom();

    List<Event> findAllByDate(Date date);

    List<Event> findAllByCategory(Category category);

    List<Event> findAllByStatus(String status);


}
