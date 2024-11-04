package com.website.system.product.datamodel.antiwrinklecream;

import com.website.system.product.antiwrinklecream.SkinType;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.datamodel.Product;
import jakarta.persistence.*;

@Entity
public class AntiWrinkleCream extends Product {
    private int ageGroup;
    @Enumerated(EnumType.STRING)
    private com.website.system.product.antiwrinklecream.SkinType skinType;

    public AntiWrinkleCream() {

    }

    public AntiWrinkleCream(String name, double price, int quantity, ProductType productType, int ageGroup, SkinType skinType) {
        super(name, price, quantity, productType);
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
        return super.toString() + ", AntiWrinkleCream{" +
                "ageGroup=" + ageGroup +
                ", skinType=" + skinType +
                '}';
    }
}
