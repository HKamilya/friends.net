package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.model.Category;
import ru.itis.javalab.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service

public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoriesServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
