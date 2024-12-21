package com.ism.data.repository;

import com.ism.data.entites.Detail;

import java.util.List;

public interface DetailRepository {
    Detail selectById(int id);
    List<Detail> selectAll();
    void insert(Detail detail);
    void delete(int id); 

}

