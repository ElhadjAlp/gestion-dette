package com.ism.data.repository.jpa.impl;

import java.util.List;

import com.ism.core.Repository.impl.RepositoryJpaImpl;
import com.ism.data.entites.User;

public class UserRepositoryJPA  extends RepositoryJpaImpl <User>{
     @Override
    public List<User> selectAll() {
        
        return this.em
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
