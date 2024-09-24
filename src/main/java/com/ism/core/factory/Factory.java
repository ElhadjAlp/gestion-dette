package com.ism.core.factory;

import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.UserRepository;
import com.ism.data.repository.bd.ClientRepositoryBD;
import com.ism.data.repository.bd.UserRepositoryBD;

public class Factory {
    private Factory() {

    }

    private static ClientRepository clientRepository = null;
    private static UserRepository userRepository = null;

    public static ClientRepository getInstanceClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientRepositoryBD(getInstanceUserRepository());
        }
        return clientRepository;
    }

    public static UserRepository getInstanceUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryBD();
        }
        return userRepository;
    }

}
