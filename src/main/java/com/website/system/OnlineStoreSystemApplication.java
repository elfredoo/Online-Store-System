package com.website.system;

import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.ClientManager;
import com.website.system.dataloader.TestDataLoader;
import com.website.system.discount.*;
import com.website.system.order.OrderService;
import com.website.system.product.ProductManager;
import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.datamodel.antiwrinklecream.AntiWrinkleCream;
import com.website.system.product.datamodel.antiwrinklecream.SkinType;
import com.website.system.product.dto.ProductDtoMapper;
import com.website.system.ui.cmd.CommandLineInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@EnableAsync
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        CommandLineInterface cmdInterface = context.getBean(CommandLineInterface.class);
        TestDataLoader testDataLoader = context.getBean(TestDataLoader.class);
        testDataLoader.loadTestData();
        cmdInterface.start();

    }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}