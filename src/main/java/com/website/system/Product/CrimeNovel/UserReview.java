package com.website.system.Product.CrimeNovel;

import jakarta.persistence.*;

@Entity
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "crime_novel_id", nullable = false)
    private CrimeNovel crimeNovel;

    public UserReview() {
    }

    public UserReview(double rating, String comment, CrimeNovel crimeNovel) {
        this.rating = rating;
        this.comment = comment;
        this.crimeNovel = crimeNovel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CrimeNovel getCrimeNovel() {
        return crimeNovel;
    }

    public void setCrimeNovel(CrimeNovel crimeNovel) {
        this.crimeNovel = crimeNovel;
    }
}
