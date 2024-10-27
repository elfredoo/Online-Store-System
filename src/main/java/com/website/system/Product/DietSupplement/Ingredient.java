package com.website.system.Product.DietSupplement;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "ingredients")
    private List<DietSupplement> dietSupplements;

    public Ingredient() {
    }

    public Ingredient(String name, List<DietSupplement> dietSupplements) {
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
