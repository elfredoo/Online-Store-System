package com.website.system.Product.CrimeNovel;

import com.website.system.Product.Product;
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

    public CrimeNovel(String name, double price, int quantity, List<UserReview> userReviews) {
        super(name, price, quantity);
        this.userReviews = userReviews;
    }

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
