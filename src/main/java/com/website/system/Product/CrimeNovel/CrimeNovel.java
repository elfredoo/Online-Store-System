package com.website.system.Product.CrimeNovel;

import com.website.system.Product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CrimeNovel extends Product {
    private double averageRating;
    @OneToMany(
            mappedBy = "crimeNovel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<UserReview> userReviews;

    public CrimeNovel(Long id, String name,
                      double price, int quantity,
                      double averageRating,
                      List<UserReview> userReviews) {
        super(id, name, price, quantity);
        this.averageRating = averageRating;
        this.userReviews = userReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
