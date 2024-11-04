package com.website.system.product.datamodel.sportshoe;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class SportShoe extends Product {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_shoe_id")
    private List<ShoeSize> availableShoeSizes;

    public SportShoe() {
    }

    public SportShoe(String name, double price, int quantity, ProductType type, List<ShoeSize> availableShoeSizes) {
        super(name, price, quantity, type);
        this.availableShoeSizes = availableShoeSizes;
    }


    public List<ShoeSize> getAvailableShoeSizes() {
        return availableShoeSizes;
    }

    public void setAvailableShoeSizes(List<ShoeSize> availableShoeSizes) {
        this.availableShoeSizes = availableShoeSizes;
    }
}
