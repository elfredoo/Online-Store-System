package com.website.system.product.datamodel.winterjacket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String color;
    private boolean waterproof;
    private boolean ventilation;
    private boolean detachableHood;

    public Features() {
    }

    public Features(String size, String color, boolean waterproof, boolean ventilation, boolean detachableHood) {
        this.size = size;
        this.color = color;
        this.waterproof = waterproof;
        this.ventilation = ventilation;
        this.detachableHood = detachableHood;
    }

    @Override
    public String toString() {
        return "Features{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", waterproof=" + waterproof +
                ", ventilation=" + ventilation +
                ", detachableHood=" + detachableHood +
                '}';
    }
}
