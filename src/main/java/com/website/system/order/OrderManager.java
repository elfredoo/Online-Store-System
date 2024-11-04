package com.website.system.order;

import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.cart.ShoppingCart;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderManager {
    private final OrderDtoMapper orderDtoMapper;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final ProductManager productManager;
    private final ShoppingCartManager shoppingCartManager;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    public OrderManager(OrderDtoMapper orderDtoMapper,
                        ShoppingCartDtoMapper shoppingCartDtoMapper,
                        ProductManager productManager,
                        ShoppingCartManager shoppingCartManager,
                        OrderRepository orderRepository,
                        ProductRepository productRepository, ProductDtoMapper productDtoMapper) {
        this.orderDtoMapper = orderDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.productManager = productManager;
        this.shoppingCartManager = shoppingCartManager;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDtoMapper = productDtoMapper;
    }

     double calculateTotalPrice(ShoppingCartDto shoppingCartDto) {
        ShoppingCart cart = shoppingCartDtoMapper.map(shoppingCartDto);
        if (cart.getProducts().isEmpty()) return 0;
        double totalPrice = 0;
        for (Product product : cart.getProducts()) {
            totalPrice+=product.getPrice();
        }
        return totalPrice;
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        return orderDtoMapper.map(order);
    }

    @Transactional
    public OrderDto placeOrder(Long shoppingCartId) {
        ShoppingCartDto shoppingCartDto = shoppingCartManager.getShoppingCart(shoppingCartId);
        ShoppingCart shoppingCart = shoppingCartDtoMapper.map(shoppingCartDto);
        removeFromStock(shoppingCart.getProducts()
                .stream()
                .map(productDtoMapper::map)
                .toList());
        Order order = new Order();
        order.setClient(shoppingCart.getClient());
        order.setShoppingCart(shoppingCart);
        order.setTotalPrice(calculateTotalPrice(shoppingCartDto));
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        orderRepository.save(order);
        return orderDtoMapper.map(order);
    }

    private void removeFromStock(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream().map(productDtoMapper::map).toList();
        for (Product product : products) {
            if (product.getQuantity() <= 0) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock.");
            } else if (product.getQuantity() <= 1) {
                productManager.deleteProduct(product.getId());
            } else{
                minusQuantity(product);
                productRepository.save(product);
            }
        }
    }

    private void minusQuantity(Product product) {
        product.setQuantity(product.getQuantity() - 1);
    }
}
