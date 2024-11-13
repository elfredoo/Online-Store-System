package com.website.system.product.dto;

import com.website.system.discount.Discount;
import com.website.system.product.datamodel.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductDto implements Comparable<ProductDto> {
    private Long id;
    private String name;
    private double price;
    private ProductType productType;
    private Discount discount;

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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    private double calcPriceAfterDiscount(){
        BigDecimal price = BigDecimal.valueOf(getPrice());
        BigDecimal discountPercentage = BigDecimal.valueOf(discount.getPercentage());
        BigDecimal valueToSubtract = price.multiply(discountPercentage);
        BigDecimal amountAfterDiscount = price.subtract(valueToSubtract).setScale(2, RoundingMode.HALF_UP);
        return amountAfterDiscount.doubleValue();
    }

    @Override
    public String toString() {
        String baseString = id + "." + name + " " + price + "zł " + productType;
        if (discount !=null){
            baseString += " <- produkt objęty promocją "+discount.getName()+" o wartości: "+discount.getPercentage()+"%"+" cena po rabacie: "+calcPriceAfterDiscount()+"PLN";
        }
        return baseString;
    }
}
