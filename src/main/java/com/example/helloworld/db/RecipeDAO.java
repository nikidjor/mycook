package com.example.helloworld.db;

import com.example.helloworld.core.Category;
import com.example.helloworld.core.Recipe;
import com.example.helloworld.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class RecipeDAO extends AbstractDAO<Recipe> {
    public RecipeDAO(SessionFactory factory) {
        super(factory);
    }

    public Recipe findById(Long id) {
        return (Recipe) currentSession().get(Recipe.class, id);
    }

    public void delete(Recipe recipe) {
        currentSession().delete(recipe);
    }

    public void update(Recipe recipe) {
        currentSession().saveOrUpdate(recipe);
    }

    public Recipe create(Recipe recipe) {
        Category category = currentSession().get(Category.class, recipe.getCategory().getId());
        recipe.setCategory(category);
        User user = currentSession().get(User.class, recipe.getUser().getId());
        recipe.setUser(user);
        return persist(recipe);
    }

    public List<Recipe> findAll() {
        return list(namedQuery("com.example.helloworld.core.Recipe.findAll"));
    }
}
