package com.website.system.discount;

import com.website.system.product.datamodel.Product;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToMany
    private List<Product> applicableProducts;

    public Discount() {
    }

    public Discount(String name,
                    Double percentage,
                    LocalDate startDate,
                    LocalDate endDate,
                    List<Product> applicableProducts) {
        this.name = name;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicableProducts = applicableProducts;
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

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Product> getApplicableProducts() {
        return applicableProducts;
    }

    public void setApplicableProducts(List<Product> applicableProducts) {
        this.applicableProducts = applicableProducts;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", applicableProducts=" + applicableProducts +
                '}';
    }
}
