package com.website.system.Product;

public class Laptop extends Product{
    private String processor;
    private int ram;
    private String graphicsCard;
    private String diskType;
    private int diskCapacity;
    private double screenSize;

    public Laptop(Long id, String name,
                  double price, int quantity,
                  String processor, int ram,
                  String graphicsCard, String diskType,
                  int diskCapacity, double screenSize) {
        super(id, name, price, quantity);
        this.processor = processor;
        this.ram = ram;
        this.graphicsCard = graphicsCard;
        this.diskType = diskType;
        this.diskCapacity = diskCapacity;
        this.screenSize = screenSize;
    }
}
