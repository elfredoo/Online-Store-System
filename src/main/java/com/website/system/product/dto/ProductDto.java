package com.website.system.product.dto;

import com.website.system.product.datamodel.ProductType;

public class ProductDto implements Comparable<ProductDto> {
    private Long id;
    private String name;
    private double price;
    private ProductType productType;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, double price, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public ProductDto(String name, double price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public int compareTo(ProductDto other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id + "." + name + " " + price + "zł " + productType;
    }
}
