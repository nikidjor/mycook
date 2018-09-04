package com.example.helloworld.resources;

import com.example.helloworld.core.User;
import com.example.helloworld.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    public User createUser(User user) {
        return userDAO.create(user);
    }

    @GET
    @UnitOfWork
    public List<User> listUser() {
        return userDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public User get(@PathParam("id") Long id) {
        return userDAO.findById(id) ;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Long id) {
        userDAO.delete(userDAO.findById(id));
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public User update(@PathParam("id") Long id, User user) {
        User userDb = userDAO.findById(id);
        userDb.setUsername(user.getUsername());
        userDb.setPassword(user.getPassword());
        userDb.setEmail(user.getEmail());
        userDAO.update(userDb);
        return user;
    }


}
