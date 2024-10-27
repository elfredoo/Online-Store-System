package com.website.system.Product.Laptop;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

@Entity
public class Laptop extends Product {
    private String processor;
    private int ram;
    private String graphicsCard;
    private String diskType;
    private int diskCapacity;
    private double screenSize;

    public Laptop() {
    }

    public Laptop(String name,
                  double price,
                  int quantity,
                  String processor,
                  int ram,
                  String graphicsCard,
                  String diskType,
                  int diskCapacity,
                  double screenSize) {
        super(name, price, quantity);
        this.processor = processor;
        this.ram = ram;
        this.graphicsCard = graphicsCard;
        this.diskType = diskType;
        this.diskCapacity = diskCapacity;
        this.screenSize = screenSize;
    }
}
