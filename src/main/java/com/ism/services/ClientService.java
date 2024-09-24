package com.ism.services;

import java.util.List;

import com.ism.data.entites.Client;

public interface ClientService {
    void createClient(Client client);

    List<Client> findAllClient();

    Client searchClient(String telephone);

    Client searchClientBySurname(String surname);
}
