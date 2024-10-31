package com.website.system.Product.SportShoe;

import com.website.system.Product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.hibernate.engine.jdbc.Size;

import java.util.List;

@Entity
public class SportShoe extends Product {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_shoe_id")
    private List<ShoeSize> availableShoeSizes;

    public SportShoe() {
    }

    public SportShoe(String name, double price, int quantity, List<ShoeSize> availableShoeSizes) {
        super(name, price, quantity);
        this.availableShoeSizes = availableShoeSizes;
    }

    public List<ShoeSize> getAvailableShoeSizes() {
        return availableShoeSizes;
    }

    public void setAvailableShoeSizes(List<ShoeSize> availableShoeSizes) {
        this.availableShoeSizes = availableShoeSizes;
    }
}
