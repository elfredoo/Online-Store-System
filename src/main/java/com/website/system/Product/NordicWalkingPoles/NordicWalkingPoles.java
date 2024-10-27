package com.website.system.Product.NordicWalkingPoles;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

@Entity
public class NordicWalkingPoles extends Product {
    private double minHeight;
    private double maxHeight;
    private UserPurpose userPurpose;

    public NordicWalkingPoles() {
    }

    public NordicWalkingPoles(String name,
                              double price,
                              int quantity,
                              double minHeight,
                              double maxHeight,
                              UserPurpose userPurpose) {
        super(name, price, quantity);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.userPurpose = userPurpose;
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
}
