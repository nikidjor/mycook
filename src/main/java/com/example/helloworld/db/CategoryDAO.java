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

    public Category findById(Long id) {
        return (Category) currentSession().get(Category.class, id);
    }

    public void delete(Category category){
        currentSession().delete(category);
    }

    public void update(Category category) {
        currentSession().saveOrUpdate(category);
    }

    public Category create(Category category) {
        return persist(category);
    }

    public List<Category> findAll() {
        return list(namedQuery("com.example.helloworld.core.Category.findAll"));
    }
}
