package com.website.system.Product.Laptop;

import com.website.system.Product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Laptop extends Product {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id")
    private List<LaptopConfiguration> configuration;

    public Laptop() {

    }

    public Laptop(String name, double price, int quantity, List<LaptopConfiguration> configuration) {
        super(name, price, quantity);
        this.configuration = configuration;
    }
}
