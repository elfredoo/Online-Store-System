package com.website.system.Product.ProgrammingCourse;

import com.website.system.Product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class ProgrammingCourse extends Product {
    @OneToMany
    private List<CoursePreview> coursePreview;

    public ProgrammingCourse() {
    }

    public ProgrammingCourse(String name, double price, int quantity, List<CoursePreview> coursePreview) {
        super(name, price, quantity);
        this.coursePreview = coursePreview;
    }
}
