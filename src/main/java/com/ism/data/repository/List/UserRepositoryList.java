package com.ism.data.repository.List;

import com.ism.core.Repository.impl.RepositoryListImpl;
import com.ism.data.entites.User;
import com.ism.data.repository.UserRepository;

public class UserRepositoryList extends RepositoryListImpl<User> implements UserRepository {


    @Override
    public User selectByLogin(String login) {
        for (User user : selectAll()) {
            if (user.getLogin().equals(login)) {
                return user; 
            }
        }
        return null; 
    }

    @Override
    public User selectByID(int id) {
        for (User user : selectAll()) {
            if (user.getId() == id) {
                return user; 
            }
        }
        return null; 
    }

    public void remove(User user) {
        list.remove(user);
    }
}
