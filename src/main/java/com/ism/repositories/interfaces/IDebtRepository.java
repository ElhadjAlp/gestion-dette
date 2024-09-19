package com.ism.repositories.interfaces;

import com.ism.core.IRepositories;

public interface IDebtRepository<T> extends IRepositories<T> {
    T findById(int object);
}
