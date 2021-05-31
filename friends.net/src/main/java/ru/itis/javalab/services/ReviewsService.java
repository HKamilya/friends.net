package ru.itis.javalab.services;

import ru.itis.javalab.dto.ReviewDto;
import ru.itis.javalab.model.Review;
import ru.itis.javalab.model.User;

import java.util.List;

public interface ReviewsService {
    public void save(ReviewDto review, Long id);

    public List<Review> findAllByEventId(Long id);
}
