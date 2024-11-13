package com.website.system.product.datamodel.educationaltoy;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class EducationalToy extends Product {
    private int minAge;
    private int maxAge;
    @ManyToMany(cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", educationalPurposes=" + educationalPurposes +
                '}';
    }
}
