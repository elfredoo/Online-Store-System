package com.website.system.product.datamodel.babyclothes;

public enum Season {
    WINTER("winter"), SPRING("spring"), SUMMER("summer"), AUTUMN("autumn");

    private final String text;

    Season(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
