package com.example.helloworld.core;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "recipes")
@NamedQueries({@NamedQuery(name = "com.example.helloworld.core.Recipe.findAll", query = "SELECT r FROM Recipe r")
})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="user", nullable = false)
    private User user;

    public Recipe() {
    }

    public Recipe(String title, String description, Category category, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Recipe setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {return user;}

    public void setUser(User user) { this.user = user;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
            Objects.equals(title, recipe.title) &&
            Objects.equals(description, recipe.description) &&
            Objects.equals(category, recipe.category)&&
            Objects.equals(user, recipe.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, user);
    }
}
