package com.kshrd.hrdprojectcontrolapi.models.hpc;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;

public class Project {

    private Integer projectId;

    private String name;

    private String objective;

    private String feature;

    private Generation generation;

    private Course course;

    public Project() {
    }

    public Project(Integer projectId, String name, String objective, String feature, Generation generation, Course course) {
        this.projectId = projectId;
        this.name = name;
        this.objective = objective;
        this.feature = feature;
        this.generation = generation;
        this.course = course;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", objective='" + objective + '\'' +
                ", feature='" + feature + '\'' +
                ", generation=" + generation +
                ", course=" + course +
                '}';
    }
}
