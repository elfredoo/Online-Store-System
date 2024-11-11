package com.website.system.product.datamodel.sportshoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoeLengthToSizeConverter {
    private Map<Double, String> euSizes;

    public ShoeLengthToSizeConverter() {
        this.euSizes = new HashMap<>();
        euSizes.put(22.0, "EU 35");
        euSizes.put(22.5, "EU 36");
        euSizes.put(23.0, "EU 36.5");
        euSizes.put(23.5, "EU 37");
        euSizes.put(24.0, "EU 38");
        euSizes.put(24.5, "EU 39");
        euSizes.put(25.0, "EU 39.5");
        euSizes.put(25.5, "EU 40");
        euSizes.put(26.0, "EU 41");
        euSizes.put(26.5, "EU 41.5");
        euSizes.put(27.0, "EU 42");
        euSizes.put(27.5, "EU 42.5");
        euSizes.put(28.0, "EU 43");
        euSizes.put(28.5, "EU 44");
        euSizes.put(29.0, "EU 44.5");
        euSizes.put(29.5, "EU 45");
        euSizes.put(30.0, "EU 46");
        euSizes.put(30.5, "EU 46.5");
        euSizes.put(31.0, "EU 47");
        euSizes.put(31.5, "EU 48");
    }

     Optional<String> convert(double shoeLength){
         String shoeSize = euSizes.get(shoeLength);
         return Optional.ofNullable(shoeSize);
     }
}
