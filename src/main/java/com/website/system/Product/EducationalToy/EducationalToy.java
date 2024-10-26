package com.website.system.Product.EducationalToy;

import jakarta.persistence.ManyToMany;

import java.util.Set;

public class EducationalToy {
    private int minAge;
    private int maxAge;
    @ManyToMany
    private Set<EducationalPurpose> educationalPurposes;
}
