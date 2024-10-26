package com.website.system.Product.DietSupplement;

import jakarta.persistence.ManyToMany;

import java.util.List;

public class Ingredient {
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "ingredients")
    private List<DietSupplement> dietSupplements;

    public Ingredient(Long id, String name, List<DietSupplement> dietSupplements) {
        this.id = id;
        this.name = name;
        this.dietSupplements = dietSupplements;
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

    public List<DietSupplement> getDietSupplements() {
        return dietSupplements;
    }

    public void setDietSupplements(List<DietSupplement> dietSupplements) {
        this.dietSupplements = dietSupplements;
    }
}
