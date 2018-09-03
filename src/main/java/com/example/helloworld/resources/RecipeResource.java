package com.example.helloworld.resources;

import com.example.helloworld.core.Recipe;
import com.example.helloworld.db.RecipeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

    private final RecipeDAO recipeDAO;

    public RecipeResource(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @POST
    @UnitOfWork
    public Recipe createRecipe(Recipe recipe) {
        return recipeDAO.create(recipe);
    }

    @GET
    @UnitOfWork
    public List<Recipe> listRecipe() {
        return recipeDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Recipe get(@PathParam("id") Long id) {
        return recipeDAO.findById(id) ;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Long id) {
        recipeDAO.delete(recipeDAO.findById(id));
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Recipe update(@PathParam("id") Long id, Recipe recipe) {
        Recipe recipeDb = recipeDAO.findById(id);
        recipeDb.setTitle(recipe.getTitle());
        recipeDb.setDescription(recipe.getDescription());
        recipeDb.setCategory(recipe.getCategory());

        recipeDAO.update(recipe);
        return recipe;

    }

}
