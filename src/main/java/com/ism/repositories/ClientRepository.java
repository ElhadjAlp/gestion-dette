package com.ism.repositories;


import java.util.ArrayList;
import java.util.List;

import com.ism.core.RepositoriesImpl;
import com.ism.entities.Client;
import com.ism.repositories.interfaces.IClientRepository;

public class ClientRepository extends RepositoriesImpl<Client> implements IClientRepository<Client> {
    @Override
    public Client findByPhone(String phone) {
        return list.stream().filter(client -> client.getPhone().compareTo(phone) == 0).findFirst().orElse(null);
    }
}