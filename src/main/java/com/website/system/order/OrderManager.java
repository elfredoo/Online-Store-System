package com.website.system.order;

import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.cart.ShoppingCart;
import com.website.system.invoice.InvoiceGenerator;
import com.website.system.invoice.InvoicePdfSaver;
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
    private final OrderRepository orderRepository;

    public OrderManager(OrderDtoMapper orderDtoMapper,
                        ShoppingCartDtoMapper shoppingCartDtoMapper,
                        OrderRepository orderRepository) {
        this.orderDtoMapper = orderDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.orderRepository = orderRepository;
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

   public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
   }
}
