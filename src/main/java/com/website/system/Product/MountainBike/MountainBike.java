package com.website.system.Product.MountainBike;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

@Entity
public class MountainBike extends Product {
    private Suspension suspension;
    private FrameSize frameSize;
    private String color;

    public MountainBike() {
    }

    public MountainBike(String name, double price, int quantity, Suspension suspension, FrameSize frameSize, String color) {
        super(name, price, quantity);
        this.suspension = suspension;
        this.frameSize = frameSize;
        this.color = color;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public FrameSize getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(FrameSize frameSize) {
        this.frameSize = frameSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
