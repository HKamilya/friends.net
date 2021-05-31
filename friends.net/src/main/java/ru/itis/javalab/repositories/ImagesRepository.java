package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.Image;

import java.util.Optional;



public interface ImagesRepository extends JpaRepository<Image, Long> {

    public Image findByAddress(String address);



}
