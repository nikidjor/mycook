package com.example.helloworld.db;

import com.example.helloworld.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User findById(Long id) {
        return (User) currentSession().get(User.class, id);
    }

    public void delete(User user) {
        currentSession().delete(user);
    }

    public void update(User user) {
        currentSession().saveOrUpdate(user);
    }

    public User create(User user) {
        return persist(user);
    }

    public List<User> findAll() {
        return list(namedQuery("com.example.helloworld.core.User.findAll"));
    }
}
