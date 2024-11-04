package com.website.system.product.datamodel.smartphone;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Smartphone extends Product {
    private String color;
    private int batteryCapacity;
    @ManyToMany
    @JoinTable(name = "smartphone_accessory")
    private List<Accessory> accessories;

    public Smartphone() {
    }

    public Smartphone(String name, double price, int quantity, ProductType type, String color, int batteryCapacity, List<Accessory> accessories) {
        super(name, price, quantity, type);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = accessories;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }
}
