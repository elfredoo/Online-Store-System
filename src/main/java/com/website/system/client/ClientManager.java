package com.website.system.client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ClientManager {
    private final ClientDtoMapper clientDtoMapper;
    private final ClientRepository clientRepository;

    public ClientManager(ClientDtoMapper clientDtoMapper, ClientRepository clientRepository) {
        this.clientDtoMapper = clientDtoMapper;
        this.clientRepository = clientRepository;
    }

    public void addClient(ClientDto dto) {
        Client client = clientDtoMapper.map(dto);
        clientRepository.save(client);
    }

    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto getClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientDtoMapper.map(client);
    }

    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto){
        Client client = clientDtoMapper.map(clientDto);
        Client foundClient = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        foundClient.setFirstName(client.getFirstName());
        foundClient.setLastName(client.getLastName());
        foundClient.setCountry(client.getCountry());
        foundClient.setCity(client.getCity());
        foundClient.setStreet(client.getStreet());
        foundClient.setHomeNo(client.getHomeNo());
        foundClient.setZipCode(client.getZipCode());
        foundClient.setTimeZone(client.getTimeZone());
        return clientDtoMapper.map(foundClient);
    }

}
