package com.website.system.product.antiwrinklecream;

public enum SkinType {
    NORMAL("normal"),
    DRY("dry"),
    OILY("oily"),
    MIXED("mixed"),
    SENSITIVE("sensitive"),
    MATURE("mature");

    private final String text;

    SkinType(String text) {
        this.text = text;
    }
}
