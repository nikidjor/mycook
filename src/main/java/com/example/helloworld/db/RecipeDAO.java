package com.example.helloworld.db;

import com.example.helloworld.core.Recipe;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RecipeDAO extends AbstractDAO<Recipe> {
    public RecipeDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Recipe> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Recipe create(Recipe recipe) {
        return persist(recipe);
    }

    public List<Recipe> findAll() {
        return list(namedQuery("com.example.helloworld.core.Recipe.findAll"));
    }
}