package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.AopEntity;

public interface AopEntityRepository extends JpaRepository<AopEntity, Long> {

    AopEntity findByName(String name);
}
