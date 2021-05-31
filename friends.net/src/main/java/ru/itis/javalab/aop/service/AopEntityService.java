package ru.itis.javalab.aop.service;

import ru.itis.javalab.model.AopEntity;

import java.util.Optional;

public interface AopEntityService {

    AopEntity save(AopEntity aopEntity);

    Optional<AopEntity> findByName(String name);
}
