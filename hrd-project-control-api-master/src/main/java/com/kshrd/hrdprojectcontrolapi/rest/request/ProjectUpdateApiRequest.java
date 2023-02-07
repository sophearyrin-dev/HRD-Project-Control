package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectUpdateApiRequest {

    @JsonIgnore
    private int projectId;

    private String name;

    private String objective;

    private String feature;

    private int generationId;

    private int courseId;

    public ProjectUpdateApiRequest() {
    }

    public ProjectUpdateApiRequest(int projectId, String name, String objective, String feature, int generationId, int courseId) {
        this.projectId = projectId;
        this.name = name;
        this.objective = objective;
        this.feature = feature;
        this.generationId = generationId;
        this.courseId = courseId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ProjectUpdateApiRequest{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", objective='" + objective + '\'' +
                ", feature='" + feature + '\'' +
                ", generationId=" + generationId +
                ", courseId=" + courseId +
                '}';
    }
}
