package com.example.helloworld.resources;

import com.example.helloworld.core.Recipe;
import com.example.helloworld.db.RecipeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
