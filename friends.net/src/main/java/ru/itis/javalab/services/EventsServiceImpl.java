package ru.itis.javalab.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.model.Category;
import ru.itis.javalab.model.Event;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.EventsRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class EventsServiceImpl implements EventsService {
    @Autowired
    EventsRepository eventsRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ImagesService imagesService;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;


    @Override
    public List<Event> findByUserId(Long id) {
        return eventsRepository.findAllByCreatorId(id);
    }

    @Override
    public void save(EventDto eventDto, Long id) {
        Optional<User> userOptional = usersService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Category> category = categoriesService.findById(Long.valueOf(eventDto.getCategory_id()));

            String fileName = Paths.get(Objects.requireNonNull(eventDto.getFilePart().getOriginalFilename())).getFileName().toString();
            String ext2 = FilenameUtils.getExtension(fileName);
            String imgAddress =
                    UUID.randomUUID().toString() +
                            "-" +
                            eventDto.getFilePart().getOriginalFilename();

            try {
                IOUtils.copyLarge(
                        eventDto.getFilePart().getInputStream(),
                        new FileOutputStream(uploadDir +
                                File.separator + imgAddress
                        )
                );
                Image image = new Image();
                image.setAddress(imgAddress);
                image.setType("image/" + ext2);
                Image eventsImage = imagesService.save(image);
                Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(eventDto.getDate() + " " + eventDto.getTime());
                if (category.isPresent()) {
                    Event event = Event.builder()
                            .category(category.get())
                            .city(eventDto.getCity())
                            .creator(user)
                            .date(date1)
                            .description(eventDto.getDescription())
                            .house(eventDto.getHouse())
                            .image(eventsImage)
                            .name(eventDto.getName())
                            .status("актуально")
                            .street(eventDto.getStreet())
                            .build();
                    eventsRepository.save(event);
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        Optional<Event> event = eventsRepository.findById(id);
        if (event.isPresent()) {
            System.out.println(event.get());
            Hibernate.initialize(event.get().getRequests());
            Hibernate.initialize(event.get().getReviews());
        }
        return event;
    }

    @Override
    public List<Event> findByPage(int page, int size) {
        return null;
    }

    @Override
    public void update(Event event) {

    }

    @Override
    public List<Event> findAll() {
        return eventsRepository.findAll();
    }


    public List<Event> findAllByStatus() {
        return eventsRepository.findAllByStatus("актуально");
    }

    @Override
    @Transactional
    public Event findRandom() {
        Event event = eventsRepository.findByRandom();

        System.out.println(event);
        Hibernate.initialize(event.getRequests());
        Hibernate.initialize(event.getReviews());
        return event;
    }

    @Override
    public List<Event> search(String name, List<Long> s, String date) {
        List<Category> categories = new ArrayList<>();
        for (Long aLong : s) {
            categories.add(categoriesService.findById(aLong).get());
        }
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, 1);
            Date date2 = new Date(c.getTime().getTime());
            return eventsRepository.findAll(SpecificationUtils.byCategory(categories)
                    .and(SpecificationUtils.byDateGreater(date1))
                    .and(SpecificationUtils.byDateLess(date2))
                    .and(SpecificationUtils.byName(name))
                    .and((root, criteriaQuery, criteriaBuilder) -> {
                        root.fetch("creator");
                        root.fetch("image");
                        return null;
                    }));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static class SpecificationUtils {
        public static Specification<Event> byName(String name) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), name);
        }

        public static Specification<Event> byDateGreater(Date date) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);
        }

        public static Specification<Event> byDateLess(Date date) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("date"), date);
        }

        public static Specification<Event> byCategory(List<Category> categories) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.in(root.get("category")).value(categories);
        }
    }
}
