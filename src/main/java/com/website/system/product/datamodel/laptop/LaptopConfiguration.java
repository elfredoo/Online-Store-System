package com.website.system.product.datamodel.laptop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LaptopConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String processor;
    private int ram;
    private String graphicsCard;
    private String diskType;
    private int diskCapacity;
    private double screenSize;

    public LaptopConfiguration(String processor, int ram, String graphicsCard, String diskType, int diskCapacity, double screenSize) {
        this.processor = processor;
        this.ram = ram;
        this.graphicsCard = graphicsCard;
        this.diskType = diskType;
        this.diskCapacity = diskCapacity;
        this.screenSize = screenSize;
    }

    public LaptopConfiguration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public int getDiskCapacity() {
        return diskCapacity;
    }

    public void setDiskCapacity(int diskCapacity) {
        this.diskCapacity = diskCapacity;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}
