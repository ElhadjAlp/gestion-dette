package com.ism.services;




import java.util.List;

import com.ism.entities.Client;
import com.ism.repositories.interfaces.IClientRepository;
import com.ism.services.interfaces.IClientService;

public class ClientService implements IClientService {
    private IClientRepository<Client> clientRepository;

    public ClientService(IClientRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(Client client) {
        clientRepository.insert(client);
    }

    @Override
    public List<Client> show() {
        return clientRepository.selectAll();
    }

    @Override
    public Client fetchByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }
}