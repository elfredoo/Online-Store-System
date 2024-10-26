package com.website.system;

import com.website.system.Product.SportShoe.SportShoe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj rozmiar stopy");
        double shoeLength = Double.parseDouble(scanner.next());
        SportShoe testShoe = new SportShoe(1L, "test shoe", 123.123, 12, shoeLength);
        System.out.println(testShoe);
    }

}
