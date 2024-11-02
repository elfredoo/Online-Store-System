package com.website.system.Product.MountainBike;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class MountainBike extends Product {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mountain_bike_id")
    private List<BikeConfiguration> bikeConfigurations;

    public MountainBike() {
    }

    public MountainBike(String name, double price, int quantity, ProductType type, List<BikeConfiguration> bikeConfigurations) {
        super(name, price, quantity, type);
        this.bikeConfigurations = bikeConfigurations;
    }
}
