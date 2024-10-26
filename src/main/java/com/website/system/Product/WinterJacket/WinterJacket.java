package com.website.system.Product.WinterJacket;

import com.website.system.Product.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class WinterJacket extends Product {
    private String size;
    private String color;
    @OneToOne
    @JoinColumn(name = "additional_feature_id", unique = true)
    private AdditionalFeatures additionalFeatures;

    public WinterJacket(Long id, String name,
                        double price, int quantity,
                        String size, String color,
                        AdditionalFeatures additionalFeatures) {
        super(id, name, price, quantity);
        this.size = size;
        this.color = color;
        this.additionalFeatures = additionalFeatures;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AdditionalFeatures getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(AdditionalFeatures additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }
}
