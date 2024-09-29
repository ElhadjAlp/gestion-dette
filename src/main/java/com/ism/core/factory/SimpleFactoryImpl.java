package com.ism.core.factory;

import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.UserRepository;
import com.ism.data.repository.bd.ClientRepositoryBD;
import com.ism.data.repository.bd.UserRepositoryBD;
import com.ism.data.repository.jpa.impl.ClientRepositoryJPA;

public class SimpleFactoryImpl implements SimpleFactory {
    
    @Override
    public ClientRepository getClientRepository(String type) {
        if ("BD".equalsIgnoreCase(type)) {
            return new ClientRepositoryBD();
        } else if ("JPA".equalsIgnoreCase(type)) {
            return new ClientRepositoryJPA();
        }
        throw new IllegalArgumentException("Type de repository client inconnu : " + type);
    }

    @Override
    public UserRepository getUserRepository() {
        return new UserRepositoryBD();
    }
}



