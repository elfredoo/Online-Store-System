package com.website.system.product.datamodel.programmingcourse;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

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
