package com.website.system.product.datamodel.laptop;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Laptop extends Product {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,  orphanRemoval = true)
    @JoinColumn(name = "laptop_id")
    private List<LaptopConfiguration> configuration;

    public Laptop() {

    }

    public Laptop(String name, double price, int quantity, ProductType productType, List<LaptopConfiguration> configuration) {
        super(name, price, quantity, productType);
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", configurations=" + configuration +
                '}';
    }
}
