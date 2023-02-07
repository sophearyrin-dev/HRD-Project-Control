package com.kshrd.hrdprojectcontrolapi.rest.response;

public class ProjectApiFilterResponse {
    int id;
    String name;
    String generation;
    String course;
    public ProjectApiFilterResponse(){};

    public ProjectApiFilterResponse(int id, String name, String generation, String course) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.course = course;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProjectApiFilterResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generation='" + generation + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
