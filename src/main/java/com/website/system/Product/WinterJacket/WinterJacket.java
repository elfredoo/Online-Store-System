package com.website.system.Product.WinterJacket;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class WinterJacket extends Product {
    private String size;
    private String color;
    private boolean waterproof;
    private boolean ventilation;
    private boolean detachableHood;

    public WinterJacket() {
    }

    public WinterJacket(String name,
                        double price,
                        int quantity,
                        String size,
                        String color,
                        boolean waterproof,
                        boolean ventilation,
                        boolean detachableHood) {
        super(name, price, quantity);
        this.size = size;
        this.color = color;
        this.waterproof = waterproof;
        this.ventilation = ventilation;
        this.detachableHood = detachableHood;
    }
}
