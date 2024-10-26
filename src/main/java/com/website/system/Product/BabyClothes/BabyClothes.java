package com.website.system.Product.BabyClothes;

import com.website.system.Product.Product;

public class BabyClothes extends Product {
    private double babyHeight;
    private Season season;

    public BabyClothes(Long id, String name, double price, int quantity, double babyHeight, Season season) {
        super(id, name, price, quantity);
        this.babyHeight = babyHeight;
        this.season = season;
    }

    public double getBabyHeight() {
        return babyHeight;
    }

    public void setBabyHeight(double babyHeight) {
        this.babyHeight = babyHeight;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
