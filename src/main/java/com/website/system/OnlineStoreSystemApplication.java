package com.website.system;

import com.website.system.Cart.CartManager;
import com.website.system.Cart.ShoppingCart;
import com.website.system.Client.Client;
import com.website.system.Client.ClientManager;
import com.website.system.Order.Order;
import com.website.system.Order.OrderManager;
import com.website.system.Product.AntiWrinkleCream.AntiWrinkleCream;
import com.website.system.Product.AntiWrinkleCream.SkinType;
import com.website.system.Product.MountainBike.MountainBike;
import com.website.system.Product.NordicWalkingPoles.NordicWalkingPoles;
import com.website.system.Product.Product;
import com.website.system.Product.ProductManager;
import com.website.system.Product.ProgrammingCourse.ProgrammingCourse;
import com.website.system.Product.SportShoe.ShoeSize;
import com.website.system.Product.SportShoe.SportShoe;
import com.website.system.Product.WinterJacket.Features;
import com.website.system.Product.WinterJacket.WinterJacket;
import org.hibernate.engine.jdbc.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

@SpringBootApplication
public class OnlineStoreSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineStoreSystemApplication.class, args);
        ProductManager productManager = context.getBean(ProductManager.class);
        OrderManager orderManager = context.getBean(OrderManager.class);
        CartManager cartManager = context.getBean(CartManager.class);
        ClientManager clientManager = context.getBean(ClientManager.class);
        Client client = clientManager.getClient(2L);
        System.out.println(client.getShoppingCart());
    }
}