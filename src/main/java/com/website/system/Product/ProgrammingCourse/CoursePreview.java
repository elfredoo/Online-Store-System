package com.website.system.Product.ProgrammingCourse;

import jakarta.persistence.*;

@Entity
public class CoursePreview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "programming_course_id", nullable = false)
    private ProgrammingCourse programmingCourse;
    private String courseFragment;

    public CoursePreview() {
    }

    public CoursePreview(String description, ProgrammingCourse programmingCourse, String courseFragment) {
        this.description = description;
        this.programmingCourse = programmingCourse;
        this.courseFragment = courseFragment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProgrammingCourse getProgrammingCourse() {
        return programmingCourse;
    }

    public void setProgrammingCourse(ProgrammingCourse programmingCourse) {
        this.programmingCourse = programmingCourse;
    }
}
