package com.ism.services.Impl;


import java.util.List;

import com.ism.data.entites.Client;
import com.ism.data.repository.ClientRepository;
import com.ism.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    @Override
    public void createClient(Client client) {
        clientRepository.insert(client);
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.selectAll();
    }

    @Override
    public Client searchClient(String telephone) {
        return clientRepository.selectByTelephone(telephone);
    }

    @Override
    public Client searchClientBySurname(String surname) {
        return clientRepository.selectBySurname(surname);
    }

}