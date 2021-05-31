package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.repositories.ImagesRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;

    public ImagesServiceImpl(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }


    @Override
    public Image insert(Image adr) {
        return imagesRepository.save(adr);
    }

    public Image findByAddress(String address) {
        return imagesRepository.findByAddress(address);
    }

    @Override
    public Image save(Image image) {
        return imagesRepository.save(image);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imagesRepository.findById(id);
    }

}
