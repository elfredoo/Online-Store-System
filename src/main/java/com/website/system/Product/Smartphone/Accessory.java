package com.website.system.Product.Smartphone;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.Entity;

@Entity
public class Accessory extends Product {
    private String functionality;

    public Accessory() {
    }

    public Accessory(String name, double price, int quantity, ProductType type, String functionality) {
        super(name, price, quantity, type);
        this.functionality = functionality;
    }
}
