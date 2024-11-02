package com.website.system.Client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Client updateClient(Long id, Client client){
        Client foundClient = clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Client with id " + id + " not found"));
        client.setFirstName(foundClient.getFirstName());
        client.setLastName(foundClient.getLastName());
        foundClient.setCountry(client.getCountry());
        foundClient.setCity(client.getCity());
        foundClient.setStreet(client.getStreet());
        foundClient.setHomeNo(client.getHomeNo());
        foundClient.setZipCode(client.getZipCode());
        foundClient.setTimeZone(client.getTimeZone());
        return clientRepository.save(client);
    }

}
