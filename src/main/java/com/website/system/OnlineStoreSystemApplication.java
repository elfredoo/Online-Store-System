package com.website.system;

import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.ClientManager;
import com.website.system.discount.*;
import com.website.system.order.OrderService;
import com.website.system.product.ProductManager;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import com.website.system.ui.cmdui.CommandLineInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        ProductManager productManager = context.getBean(ProductManager.class);
        ShoppingCartManager shoppingCartManager = context.getBean(ShoppingCartManager.class);
        ClientManager clientManager = context.getBean(ClientManager.class);
        ProductDtoMapper productDtoMapper = context.getBean(ProductDtoMapper.class);
        OrderService orderService = context.getBean(OrderService.class);
        CommandLineInterface cmdInterface = context.getBean(CommandLineInterface.class);
        DiscountManager discountManager = context.getBean(DiscountManager.class);
        DiscountDtoMapper discountDtoMapper = context.getBean(DiscountDtoMapper.class);
        DiscountRepository discountRepository = context.getBean(DiscountRepository.class);

        
        cmdInterface.start();

    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}