package com.ism.data.repository.jpa.impl;

import java.util.List;
import javax.persistence.NoResultException;
import com.ism.core.Repository.impl.RepositoryJPAImp;
import com.ism.data.entites.User;
import com.ism.data.repository.UserRepository;

public class UserRepositoryJPA extends RepositoryJPAImp<User> implements UserRepository {
    public UserRepositoryJPA() {
        super(User.class);
    }

    @Override
    public List<User> selectAll() {
        return this.em
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User selectByLogin(String login) {
        try {
            return this.em
                    .createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no user is found
        }
    }

    @Override
    public User selectByID(int id) {
        return this.em.find(User.class, id);
    }

    
}

