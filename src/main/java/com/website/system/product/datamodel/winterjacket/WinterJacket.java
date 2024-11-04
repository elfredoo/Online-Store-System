package com.website.system.product.datamodel.winterjacket;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class WinterJacket extends Product {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @Override
    public String toString() {
        String featuresString = features.stream()
                .map(Features::toString)
                .collect(Collectors.joining(", "));

        return super.toString() + ", features=[" + featuresString + "]";
    }
}
