package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.Review;

import java.util.List;
import java.util.Optional;


public interface ReviewsRepository extends JpaRepository<Review, Long> {


    List<Review> findAllByEvent_Id(Long event_id);
}
