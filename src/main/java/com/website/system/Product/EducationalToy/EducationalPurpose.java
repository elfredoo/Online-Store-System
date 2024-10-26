package com.website.system.Product.EducationalToy;

import jakarta.persistence.ManyToMany;

import java.util.List;

public class EducationalPurpose {
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "educationalPurposes")
    private List<EducationalToy> educationalToy;
}
