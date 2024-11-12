package com.website.system;

import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.ClientManager;
import com.website.system.order.OrderManager;
import com.website.system.order.OrderService;
import com.website.system.product.ProductManager;
import com.website.system.product.dto.ProductDtoMapper;
import com.website.system.ui.cmdui.CommandLineInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        ProductManager productManager = context.getBean(ProductManager.class);
        OrderManager orderManager = context.getBean(OrderManager.class);
        ShoppingCartManager shoppingCartManager = context.getBean(ShoppingCartManager.class);
        ClientManager clientManager = context.getBean(ClientManager.class);
        ProductDtoMapper productDtoMapper = context.getBean(ProductDtoMapper.class);
        OrderService orderService = context.getBean(OrderService.class);
        CommandLineInterface cmdInterface = context.getBean(CommandLineInterface.class);
        cmdInterface.start();
//        ClientDto dto = clientManager.getClientByFirstNameLastNamePassword("John", "Doe", "password123");
//        System.out.println(dto);
    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}