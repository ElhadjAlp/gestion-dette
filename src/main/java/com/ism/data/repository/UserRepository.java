package com.ism.data.repository;

import com.ism.core.Repository.Repository;
import com.ism.data.entites.User;

public interface UserRepository extends Repository<User> {
    User selectByLogin(String login);
    User selectByID(int id);
}