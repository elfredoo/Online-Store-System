package com.website.system.product.datamodel.crimenovel;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class CrimeNovel extends Product {
    @OneToMany(
            mappedBy = "crimeNovel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<UserReview> userReviews;

    public CrimeNovel() {
    }

    public CrimeNovel(String name, double price, int quantity, ProductType type, List<UserReview> userReviews) {
        super(name, price, quantity, type);
        this.userReviews = userReviews;
    }

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
