package com.website.system.Product.DietSupplement;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import org.hibernate.mapping.Property;

import java.util.Set;

@Entity
public class DietSupplement extends Product {
    @ManyToMany
    private Set<Ingredient> ingredients;
    private String properties;

    public DietSupplement() {

    }

    public DietSupplement(String name, double price, int quantity, ProductType type, Set<Ingredient> ingredients, String properties) {
        super(name, price, quantity, type);
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
