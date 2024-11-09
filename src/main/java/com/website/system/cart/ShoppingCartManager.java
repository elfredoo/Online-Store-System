package com.website.system.cart;

import com.website.system.client.Client;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductNotFoundException;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShoppingCartManager {
    private final ProductDtoMapper productDtoMapper;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductManager productManager;

    public ShoppingCartManager(ProductDtoMapper productDtoMapper,
                               ShoppingCartDtoMapper shoppingCartDtoMapper,
                               ShoppingCartRepository shoppingCartRepository,
                               ProductManager productManager) {
        this.productDtoMapper = productDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productManager = productManager;
    }

    @Transactional
    public void clearShoppingCart(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new);
        shoppingCart.getProducts().clear();
        shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(Long id){
        shoppingCartRepository.deleteById(id);
    }

    public ShoppingCartDto createShoppingCart(Client client){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setClient(client);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartDtoMapper.map(savedShoppingCart);
    }

    @Transactional
    public void addProduct(Long shoppingCartId,Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        Product product = productManager.getProductById(productId).orElseThrow(ProductNotFoundException::new);
        shoppingCart.addProduct(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public void addProducts(Long shoppingCartId,List<Long> productIds) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(ShoppingCartNotFoundException::new);
        productIds.forEach(productId -> {
            Product product = productManager.getProductById(productId).orElseThrow(ProductNotFoundException::new);
            shoppingCart.addProduct(product);
        });
        shoppingCartRepository.save(shoppingCart);
    }

    public List<ProductDto> getProductDtos(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new);
        return shoppingCart.getProducts().stream().map(productDtoMapper::map).toList();
    }

    public ShoppingCartDto getShoppingCart(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No shopping cart found with provided ID:" + id));
        return shoppingCartDtoMapper.map(shoppingCart);
    }


}
