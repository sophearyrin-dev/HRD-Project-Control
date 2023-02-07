package com.kshrd.hrdprojectcontrolapi.rest.response;

public class TaskProgressOfAllProjectApiResponse {

    private int projectId;

    private String projectName;

    private int totalBaseTaskPercentage;

    private float projectProgressPercentage;

    private String generationName;

    private String courseName;

    public TaskProgressOfAllProjectApiResponse() {}

    public TaskProgressOfAllProjectApiResponse(int projectId, String projectName, int totalBaseTaskPercentage, float projectProgressPercentage, String generationName, String courseName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.totalBaseTaskPercentage = totalBaseTaskPercentage;
        this.projectProgressPercentage = projectProgressPercentage;
        this.generationName = generationName;
        this.courseName = courseName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getTotalBaseTaskPercentage() {
        return totalBaseTaskPercentage;
    }

    public void setTotalBaseTaskPercentage(int totalBaseTaskPercentage) {
        this.totalBaseTaskPercentage = totalBaseTaskPercentage;
    }

    public float getProjectProgressPercentage() {
        return projectProgressPercentage;
    }

    public void setProjectProgressPercentage(float projectProgressPercentage) {
        this.projectProgressPercentage = projectProgressPercentage;
    }

    public String getGenerationName() {
        return generationName;
    }

    public void setGenerationName(String generationName) {
        this.generationName = generationName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "TaskProgressOfAllProjectApiResponse{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", totalBaseTaskPercentage=" + totalBaseTaskPercentage +
                ", projectProgressPercentage=" + projectProgressPercentage +
                ", generationName='" + generationName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
