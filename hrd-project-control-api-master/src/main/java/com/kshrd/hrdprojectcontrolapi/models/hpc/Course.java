package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class Course {

    private Integer courseId;

    private String name;

    public Course() {
    }

    public Course(Integer courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                '}';
    }
}
