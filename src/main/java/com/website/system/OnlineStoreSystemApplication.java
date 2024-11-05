package com.website.system;

import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.ClientManager;
import com.website.system.order.OrderDto;
import com.website.system.order.OrderManager;
import com.website.system.order.OrderProcessor;
import com.website.system.product.ProductManager;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        ProductManager productManager = context.getBean(ProductManager.class);
        OrderManager orderManager = context.getBean(OrderManager.class);
        ShoppingCartManager shoppingCartManager = context.getBean(ShoppingCartManager.class);
        ClientManager clientManager = context.getBean(ClientManager.class);
        ProductDtoMapper productDtoMapper = context.getBean(ProductDtoMapper.class);
        OrderProcessor orderProcessor = context.getBean(OrderProcessor.class);
    }
}