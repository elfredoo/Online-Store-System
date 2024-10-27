package com.website.system.Product.SportShoe;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;

import java.util.Optional;

@Entity
public class SportShoe extends Product {
    private String size;

    public SportShoe() {
    }

    public SportShoe(String name, double price, int quantity, String size) {
        super(name, price, quantity);
        this.size = size;
    }

//    public SportShoe(Long id, String name,
//                     double price, int quantity,
//                     double shoeLength) {
//        super(id, name, price, quantity);
//        ShoeLengthToSizeConverter converter = new ShoeLengthToSizeConverter();
//        Optional<String> shoeSize = converter.convert(shoeLength);
//        if (shoeSize.isPresent()) {
//            this.size = shoeSize.get();
//        }else {
//            throw new NoSuchShoeSizeException("No shoe size found for shoe lenght: " + shoeLength);
//        }
//    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SportShoe{" +
                "size='" + size + '\'' +
                '}';
    }
}
