package com.website.system.Product.BabyClothes;

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
