package com.website.system.Product.ProgrammingCourse;

import com.website.system.Product.Product;
import com.website.system.Product.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class ProgrammingCourse extends Product {
    @OneToMany(mappedBy = "programmingCourse")
    private List<CoursePreview> coursePreview;

    public ProgrammingCourse() {
    }

    public ProgrammingCourse(String name, double price, int quantity, ProductType type, List<CoursePreview> coursePreview) {
        super(name, price, quantity, type);
        this.coursePreview = coursePreview;
    }
}
