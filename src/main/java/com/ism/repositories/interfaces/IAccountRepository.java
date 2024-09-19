package com.ism.repositories.interfaces;

import com.ism.core.IRepositories;

public interface IAccountRepository<T> extends IRepositories<T> {
    T findByLoginAndPassword(String login, String password);
    T findById(int object);
}
