package com.website.system.Product.Smartphone;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

@Entity
public class Accessory extends Product {
    private String functionality;

    public Accessory() {
    }

    public Accessory(String name, double price, int quantity, String functionality) {
        super(name, price, quantity);
        this.functionality = functionality;
    }
}
