package com.example.helloworld.core;

import javax.persistence.*;
import java.util.Objects;

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
    @JoinColumn(name = "category")
    private Category category;

    public Recipe() {
    }

    public Recipe(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
            Objects.equals(title, recipe.title) &&
            Objects.equals(description, recipe.description) &&
            Objects.equals(category, recipe.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category);
    }
}
