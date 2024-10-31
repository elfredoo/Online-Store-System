package com.website.system.Product.SportShoe;

import jakarta.persistence.*;

@Entity
public class ShoeSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double size;
    private double shoeLength;
    private double minFootLength;
    private double maxFootLength;

    public ShoeSize() {
    }

    public ShoeSize(double size, double shoeLength, double minFootLength, double maxFootLength) {
        this.size = size;
        this.shoeLength = shoeLength;
        this.minFootLength = minFootLength;
        this.maxFootLength = maxFootLength;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getShoeLength() {
        return shoeLength;
    }

    public void setShoeLength(double shoeLength) {
        this.shoeLength = shoeLength;
    }

    public double getMinFootLength() {
        return minFootLength;
    }

    public void setMinFootLength(double minFootLength) {
        this.minFootLength = minFootLength;
    }

    public double getMaxFootLength() {
        return maxFootLength;
    }

    public void setMaxFootLength(double maxFootLength) {
        this.maxFootLength = maxFootLength;
    }

    @Override
    public String toString() {
        return "ShoeSize{" +
                "id=" + id +
                ", size=" + size +
                ", shoeLength=" + shoeLength +
                ", minFootLength=" + minFootLength +
                ", maxFootLength=" + maxFootLength +
                '}';
    }
}
