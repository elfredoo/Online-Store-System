package com.website.system.product.datamodel.babyclothes;
import com.website.system.product.babyclothes.Season;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.datamodel.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class BabyClothes extends Product {
    private double babyHeight;
    @Enumerated(EnumType.STRING)
    private Season season;

    public BabyClothes() {

    }

    public BabyClothes(String name, double price, int quantity, ProductType productType, double babyHeight, Season season) {
        super(name, price, quantity, productType);
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
