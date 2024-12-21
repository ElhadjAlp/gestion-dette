package com.ism.data.repository;

import com.ism.core.Repository.Repository;
import com.ism.data.entites.Dette;

public interface DetteRepository extends Repository<Dette> {
    Dette selectById(int id);
    void updateDette(Dette dette); 
    void deleteDette(int id); 
}

