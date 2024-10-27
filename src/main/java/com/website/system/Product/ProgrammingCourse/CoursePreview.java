package com.website.system.Product.ProgrammingCourse;

import jakarta.persistence.*;

@Entity
public class CoursePreview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "programming_course_id")
    private ProgrammingCourse programmingCourse;
    private String description;
    private String courseFragment;

    public CoursePreview() {
    }

    public CoursePreview(ProgrammingCourse programmingCourse, String description, String courseFragment) {
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
