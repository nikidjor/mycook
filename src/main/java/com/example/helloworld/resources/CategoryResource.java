package com.example.helloworld.resources;

import com.example.helloworld.core.Category;
import com.example.helloworld.db.CategoryDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
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

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Category get(@PathParam("id") Long id) {
        return categoryDAO.findById(id) ;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Long id) {
        categoryDAO.delete(categoryDAO.findById(id));
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Category update(@PathParam("id") Long id, Category category) {
        category = category.setId(id);
        categoryDAO.update(category);

        return category;
    }


}
