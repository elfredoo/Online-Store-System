package com.website.system.product.datamodel.mountainbike;

import jakarta.persistence.*;

@Entity
public class BikeConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String configurationName;
    @Enumerated(EnumType.STRING)
    private Suspension suspension;
    @Enumerated(EnumType.STRING)
    private FrameSize frameSize;
    private String color;

    public BikeConfiguration() {
    }

    public BikeConfiguration(String configurationName, Suspension suspension, FrameSize frameSize, String color) {
        this.configurationName = configurationName;
        this.suspension = suspension;
        this.frameSize = frameSize;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public FrameSize getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(FrameSize frameSize) {
        this.frameSize = frameSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
