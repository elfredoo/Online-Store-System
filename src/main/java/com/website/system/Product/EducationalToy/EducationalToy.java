package com.website.system.Product.EducationalToy;

import com.website.system.Product.Product;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class EducationalToy extends Product {
    private int minAge;
    private int maxAge;
    @ManyToMany
    private Set<EducationalPurpose> educationalPurposes;

    public EducationalToy(Long id, String name,
                          double price, int quantity,
                          int minAge, int maxAge,
                          Set<EducationalPurpose> educationalPurposes) {
        super(id, name, price, quantity);
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.educationalPurposes = educationalPurposes;
    }
}
