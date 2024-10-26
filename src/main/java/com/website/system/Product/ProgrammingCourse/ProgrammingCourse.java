package com.website.system.Product.ProgrammingCourse;

import com.website.system.Product.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class ProgrammingCourse extends Product {
    @OneToOne
    @JoinColumn(name = "course_preview_id")
    private CoursePreview coursePreview;

    public ProgrammingCourse(Long id, String name,
                             double price, int quantity,
                             CoursePreview coursePreview) {
        super(id, name, price, quantity);
        this.coursePreview = coursePreview;
    }
}
