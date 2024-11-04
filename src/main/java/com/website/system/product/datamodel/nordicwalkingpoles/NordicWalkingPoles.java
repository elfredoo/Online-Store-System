package com.website.system.product.datamodel.nordicwalkingpoles;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class NordicWalkingPoles extends Product {
    private double minHeight;
    private double maxHeight;
    @Enumerated(EnumType.STRING)
    private UserPurpose userPurpose;
    private double polesHeight;

    public NordicWalkingPoles() {
    }

    public NordicWalkingPoles(String name, double price, int quantity, ProductType productType, double minHeight, double maxHeight, UserPurpose userPurpose, double polesHeight) {
        super(name, price, quantity, productType);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.userPurpose = userPurpose;
        this.polesHeight = polesHeight;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(double minHeight) {
        this.minHeight = minHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public UserPurpose getUserPurpose() {
        return userPurpose;
    }

    public void setUserPurpose(UserPurpose userPurpose) {
        this.userPurpose = userPurpose;
    }

    public double getPolesHeight() {
        return polesHeight;
    }

    public void setPolesHeight(double polesHeight) {
        this.polesHeight = polesHeight;
    }
}
