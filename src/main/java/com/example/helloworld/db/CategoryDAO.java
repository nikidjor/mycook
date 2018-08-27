package com.example.helloworld.db;

import com.example.helloworld.core.Category;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Category create(Category category) {
        return persist(category);
    }

    public List<Category> findAll() {
        return list(namedQuery("com.example.helloworld.core.Category.findAll"));
    }
}
