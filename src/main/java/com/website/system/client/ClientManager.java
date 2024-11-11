package com.website.system.client;

import jakarta.validation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ClientManager {
    private final ClientDtoMapper clientDtoMapper;
    private final ClientRepository clientRepository;

    public ClientManager(ClientDtoMapper clientDtoMapper, ClientRepository clientRepository) {
        this.clientDtoMapper = clientDtoMapper;
        this.clientRepository = clientRepository;
    }

    public ClientDto addClient(Client client) {
        Client savedClient = clientRepository.save(client);
        return clientDtoMapper.map(savedClient);
    }

    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto getClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientDtoMapper.map(client);
    }



    public ClientDto verifyLoginData(String email, String password){
        Client client = clientRepository.findByEmail(email).orElseThrow(ClientNotFoundException::new);
        if (client.getPassword().equals(password)){
            return clientDtoMapper.map(client);
        }
        throw new InvalidPasswordException();
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

    public Client validateData(Client potentialClient) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        Set<ConstraintViolation<Client>> violations = validator.validate(potentialClient);

        if (violations.isEmpty()) {
            clientRepository.findByEmail(potentialClient.getEmail()).ifPresent(client -> {throw new EmailAlreadyTakenException();});
            return potentialClient;
        }else {
            throw new ConstraintViolationException(violations);
        }
    }
}
