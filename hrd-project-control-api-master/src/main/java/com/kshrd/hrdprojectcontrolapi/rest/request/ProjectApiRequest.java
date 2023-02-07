package com.kshrd.hrdprojectcontrolapi.rest.request;

public class ProjectApiRequest {

    private String name;

    private String objective;

    private String feature;

    private Integer generationId;

    private Integer courseId;

    public ProjectApiRequest() {
    }

    public ProjectApiRequest(String name, String objective, String feature, Integer generationId, Integer courseId) {
        this.name = name;
        this.objective = objective;
        this.feature = feature;
        this.generationId = generationId;
        this.courseId = courseId;
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

    public Integer getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Integer generationId) {
        this.generationId = generationId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ProjectDetailApiRequest{" +
                "name='" + name + '\'' +
                ", objective='" + objective + '\'' +
                ", feature='" + feature + '\'' +
                ", generationId=" + generationId +
                ", courseId=" + courseId +
                '}';
    }
}
