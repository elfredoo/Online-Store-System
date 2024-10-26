package com.website.system.Product.DietSupplement;

import com.website.system.Product.Product;
import jakarta.persistence.ManyToMany;
import org.hibernate.mapping.Property;

import java.util.Set;

public class DietSupplement extends Product {
    @ManyToMany
    private Set<Ingredient> ingredients;
    private String properties;

    public DietSupplement(Long id, String name,
                          double price, int quantity,
                          Set<Ingredient> ingredients, String properties) {
        super(id, name, price, quantity);
        this.ingredients = ingredients;
        this.properties = properties;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
