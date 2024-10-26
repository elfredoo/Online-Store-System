package com.website.system.Product.ProgrammingCourse;

import com.website.system.Product.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class ProgrammingCourse extends Product {
    @OneToMany
    private List<CoursePreview> coursePreview;

    public ProgrammingCourse(Long id, String name,
                             double price, int quantity,
                             List<CoursePreview> coursePreview) {
        super(id, name, price, quantity);
        this.coursePreview = coursePreview;
    }
}
