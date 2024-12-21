package com.ism.data.repository;

import com.ism.core.Repository.Repository;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;

public interface ClientRepository  extends Repository<Client>{
    Client selectByTelephone(String telephone);
    Client selectBySurname(String surname);
    void updateDette(Dette dette); 
    void deleteDette(int id); 
    Client selectById(int id);
}
