package com.ism.core;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoriesImpl<T> implements IRepositories<T> {
    protected List<T> list = new ArrayList<>();

    @Override
    public void insert(T object) {
        list.add(object);
    }

    @Override
    public List<T> selectAll() {
        return list;
    }
}
