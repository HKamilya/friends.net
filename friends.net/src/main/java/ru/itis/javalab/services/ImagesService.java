package ru.itis.javalab.services;

import ru.itis.javalab.model.Image;

import java.util.Optional;

public interface ImagesService {

    public Image insert(Image adr);

    public Optional<Image> findById(Long id);

    public Image findByAddress(String address);


    Image save(Image image);
}
