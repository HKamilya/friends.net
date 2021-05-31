package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.ReviewDto;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Review;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.ReviewsRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private EventsService eventsService;
    @Autowired
    private UsersService usersService;


    public List<Review> findAllByEventId(Long id) {
        return reviewsRepository.findAllByEvent_Id(id);

    }

    @Override
    public void save(ReviewDto reviewDto, Long id) {
        Optional<User> userOptional = usersService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Event> eventOptional = eventsService.findById(reviewDto.getEvent_id());
            if (eventOptional.isPresent()) {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

                Review review = Review.builder()
                        .date(dateNow)
                        .event(eventOptional.get())
                        .user(user)
                        .text(reviewDto.getReview())
                        .build();
                reviewsRepository.save(review);
            }
        }
    }
}
