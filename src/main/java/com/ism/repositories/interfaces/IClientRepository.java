package com.ism.repositories.interfaces;

import com.ism.core.IRepositories;

public interface IClientRepository<T> extends IRepositories<T>{
    T findByPhone(String object);
}
