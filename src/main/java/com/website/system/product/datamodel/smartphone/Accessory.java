package com.website.system.product.datamodel.smartphone;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
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
