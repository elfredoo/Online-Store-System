package com.website.system.Product.ProgrammingCourse;

import jakarta.persistence.OneToOne;

public class CoursePreview {
    private Long id;
    private ProgrammingCourse programmingCourse;
    private String description;
    private String courseFragment;

    public CoursePreview(Long id, ProgrammingCourse programmingCourse, String description, String courseFragment) {
        this.id = id;
        this.programmingCourse = programmingCourse;
        this.description = description;
        this.courseFragment = courseFragment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProgrammingCourse getProgrammingCourse() {
        return programmingCourse;
    }

    public void setProgrammingCourse(ProgrammingCourse programmingCourse) {
        this.programmingCourse = programmingCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseFragment() {
        return courseFragment;
    }

    public void setCourseFragment(String courseFragment) {
        this.courseFragment = courseFragment;
    }
}
