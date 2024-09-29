package com.ism.data.repository.jpa.impl;

import com.ism.core.Repository.impl.RepositoryJpaImpl;
import com.ism.data.entites.Client;
import com.ism.data.repository.ClientRepository;
import java.util.List;

public class ClientRepositoryJPA extends RepositoryJpaImpl<Client> implements ClientRepository {

    @Override
    public List<Client> selectAll() {
        
        return this.em
                .createQuery("SELECT u FROM Client u", Client.class)
                .getResultList();
    }

    @Override
    public Client selectBySurname(String surname) {
        return selectAll().stream()
                .filter(cl -> cl.getSurname().compareTo(surname) == 0)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Client selectByTelephone(String telephone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByTelephone'");
    }
}

