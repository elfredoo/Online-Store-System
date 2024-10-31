package com.website.system.Product.AntiWrinkleCream;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class AntiWrinkleCream extends Product {
    private int ageGroup;
    @Enumerated(EnumType.STRING)
    private SkinType skinType;

    public AntiWrinkleCream() {

    }

    public AntiWrinkleCream(String name, double price, int quantity, int ageGroup, SkinType skinType) {
        super(name, price, quantity);
        this.ageGroup = ageGroup;
        this.skinType = skinType;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    @Override
    public String toString() {
        return "AntiWrinkleCream{" +
                "ageGroup=" + ageGroup +
                ", skinType=" + skinType +
                '}';
    }
}
