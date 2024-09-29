package com.ism.core.factory;

import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.UserRepository;

public interface SimpleFactory {
    ClientRepository getClientRepository(String type);
    UserRepository getUserRepository();
}

