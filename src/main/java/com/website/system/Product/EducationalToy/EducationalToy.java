package com.website.system.Product.EducationalToy;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class EducationalToy extends Product {
    private int minAge;
    private int maxAge;
    @ManyToMany
    private Set<EducationalPurpose> educationalPurposes;

    public EducationalToy() {
    }

    public EducationalToy(String name, double price, int quantity, ProductType productType, int minAge, int maxAge, Set<EducationalPurpose> educationalPurposes) {
        super(name, price, quantity, productType);
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.educationalPurposes = educationalPurposes;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Set<EducationalPurpose> getEducationalPurposes() {
        return educationalPurposes;
    }

    public void setEducationalPurposes(Set<EducationalPurpose> educationalPurposes) {
        this.educationalPurposes = educationalPurposes;
    }
}
