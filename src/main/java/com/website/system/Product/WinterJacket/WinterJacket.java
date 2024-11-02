package com.website.system.Product.WinterJacket;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class WinterJacket extends Product {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "winter_jacket_id")
    private List<Features> features;

    public WinterJacket() {
    }

    public WinterJacket(String name, double price, int quantity, ProductType type, List<Features> features) {
        super(name, price, quantity, type);
        this.features = features;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}
