package ru.itis.javalab.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.aop.service.AopEntityService;
import ru.itis.javalab.model.AopEntity;
import ru.itis.javalab.repositories.AopEntityRepository;

import java.util.Optional;

@Service
public class AopEntityServiceImpl implements AopEntityService {
    @Autowired
    private AopEntityRepository aopEntityRepository;

    @Override
    public AopEntity save(AopEntity aopEntity) {
        return aopEntityRepository.save(aopEntity);
    }

    @Override
    public Optional<AopEntity> findByName(String name) {
        return Optional.ofNullable(aopEntityRepository.findByName(name));
    }
}
