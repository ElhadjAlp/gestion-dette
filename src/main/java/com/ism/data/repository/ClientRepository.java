package com.ism.data.repository;

import com.ism.core.Repository.Repository;
import com.ism.data.entites.Client;

public interface ClientRepository  extends Repository<Client>{
    Client selectByTelephone(String telephone);
    Client selectBySurname(String surname);
}
