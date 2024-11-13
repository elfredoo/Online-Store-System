package com.website.system.product.datamodel.crimenovel;

import jakarta.persistence.*;

@Entity
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String content;
    @ManyToOne
    @JoinColumn(name = "crime_novel_id", nullable = false)
    private CrimeNovel crimeNovel;

    public UserReview() {
    }

    public UserReview(double rating, String content, CrimeNovel crimeNovel) {
        this.rating = rating;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String comment) {
        this.content = comment;
    }

    public CrimeNovel getCrimeNovel() {
        return crimeNovel;
    }

    public void setCrimeNovel(CrimeNovel crimeNovel) {
        this.crimeNovel = crimeNovel;
    }

    @Override
    public String toString() {
        return "UserReview{" +
                "id=" + id +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", crimeNovel=" + crimeNovel +
                '}';
    }
}
