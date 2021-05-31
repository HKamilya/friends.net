package ru.itis.javalab.services;

import ru.itis.javalab.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {


    public Optional<Category> findById(Long id);

    public List<Category> findAll();

}
