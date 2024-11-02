package com.website.system.Client;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClientManager {
    private final ClientRepository clientRepository;

    public ClientManager(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Client with id " + id + " not found"));
    }


}
