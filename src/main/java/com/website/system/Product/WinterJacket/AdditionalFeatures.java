package com.website.system.Product.WinterJacket;

public class AdditionalFeatures {
    private boolean waterproof;
    private boolean ventilation;
    private boolean detachableHood;

    public AdditionalFeatures(boolean waterproof, boolean ventilation, boolean detachableHood) {
        this.waterproof = waterproof;
        this.ventilation = ventilation;
        this.detachableHood = detachableHood;
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }

    public boolean isVentilation() {
        return ventilation;
    }

    public void setVentilation(boolean ventilation) {
        this.ventilation = ventilation;
    }

    public boolean isDetachableHood() {
        return detachableHood;
    }

    public void setDetachableHood(boolean detachableHood) {
        this.detachableHood = detachableHood;
    }
}
