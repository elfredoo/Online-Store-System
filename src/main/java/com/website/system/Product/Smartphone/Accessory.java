package com.website.system.Product.Smartphone;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

@Entity
public class Accessory extends Product {
    private String function;

    public Accessory() {
    }

    public Accessory(String name, double price, int quantity, String function) {
        super(name, price, quantity);
        this.function = function;
    }
}
