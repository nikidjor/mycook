package com.example.helloworld.resources;

import com.example.helloworld.core.Category;
import com.example.helloworld.db.CategoryDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CategoryDAO categoryDAO;

    public CategoryResource(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @POST
    @UnitOfWork
    public Category createCategory(Category category) {
        return categoryDAO.create(category);
    }

    @GET
    @UnitOfWork
    public List<Category> listCategory() {
        return categoryDAO.findAll();
    }

}
