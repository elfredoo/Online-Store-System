package com.website.system.cart;

import com.website.system.client.Client;
import com.website.system.client.ClientNotFoundException;
import com.website.system.client.ClientRepository;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartDtoMapper {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartDtoMapper(ProductRepository productRepository,
                                 ClientRepository clientRepository,
                                 ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCartDto map(ShoppingCart shoppingCart) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCart.getId());
        dto.setClientId(shoppingCart.getClient().getId());
        dto.setProductsIds(shoppingCart.getProducts()
                .stream()
                .map(Product::getId)
                .toList());
        return dto;
    }

    public ShoppingCart map(ShoppingCartDto shoppingCartDto) {
        Optional<ShoppingCart> cartById = shoppingCartRepository.findById(shoppingCartDto.getId());
        if (cartById.isPresent()) {
            return cartById.get();
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        Client client = clientRepository.
                findById(shoppingCartDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        shoppingCart.setClient(client);
        List<Product> productsList = shoppingCartDto
                .getProductsIds()
                .stream()
                .map(productRepository::findById)
                .map(Optional::get).toList();
        shoppingCart.setProducts(productsList);
        return shoppingCart;
    }
}
