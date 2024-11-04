package com.website.system.client;

import com.website.system.cart.ShoppingCart;
import com.website.system.cart.ShoppingCartNotFoundException;
import com.website.system.cart.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDtoMapper {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ClientRepository clientRepository;

    public ClientDtoMapper(ShoppingCartRepository shoppingCartRepository,
                           ClientRepository clientRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.clientRepository = clientRepository;
    }

    ClientDto map(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setTimeZone(client.getTimeZone());
        clientDto.setShoppingCartId(client.getShoppingCart().getId());
        clientDto.setPassword(client.getPassword());
        return clientDto;
    }

    Client map(ClientDto clientDto) {
        Optional<Client> clientById = clientRepository.findById(clientDto.getId());
        if (clientById.isPresent()) {
            return clientById.get();
        }
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setTimeZone(clientDto.getTimeZone());
        ShoppingCart shoppingCart = shoppingCartRepository
                .findById(clientDto.getShoppingCartId())
                .orElseThrow(ShoppingCartNotFoundException::new);
        client.setShoppingCart(shoppingCart);
        client.setPassword(clientDto.getPassword());
        return client;
    }
}
