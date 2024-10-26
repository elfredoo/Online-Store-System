package com.website.system.Product.EducationalToy;

import jakarta.persistence.ManyToMany;

import java.util.List;

public class EducationalPurpose {
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "educationalPurposes")
    private List<EducationalToy> educationalToy;

    public EducationalPurpose(Long id, String name, List<EducationalToy> educationalToy) {
        this.id = id;
        this.name = name;
        this.educationalToy = educationalToy;
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

    public List<EducationalToy> getEducationalToy() {
        return educationalToy;
    }

    public void setEducationalToy(List<EducationalToy> educationalToy) {
        this.educationalToy = educationalToy;
    }
}
