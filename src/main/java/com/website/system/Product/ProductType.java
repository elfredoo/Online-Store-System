package com.website.system.Product;

public enum ProductType {
    ANTI_WRINKLE_CREAM("AntiWrinkleCream"),
    BABY_CLOTHES("BabyClothes"),
    CRIME_NOVEL("CrimeNovel"),
    DIET_SUPPLEMENT("DietSupplement"),
    EDUCATIONAL_TOY("EducationalToy"),
    LAPTOP("Laptop"),
    MOUNTAIN_BIKE("MountainBike"),
    NORDIC_WALKING_POLES("NordicWalkingPoles"),
    PROGRAMMING_COURSE("ProgrammingCourse"),
    SMARTPHONE("Smartphone"),
    ACCESSORY("Accessory"),
    SPORT_SHOE("SportShoe"),
    WINTER_JACKET("WinterJacket");

    private final String text;

    ProductType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
