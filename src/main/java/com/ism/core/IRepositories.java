package com.ism.core;

import java.util.List;

public interface IRepositories<T> {
    void insert(T object);
    List<T> selectAll();
}
