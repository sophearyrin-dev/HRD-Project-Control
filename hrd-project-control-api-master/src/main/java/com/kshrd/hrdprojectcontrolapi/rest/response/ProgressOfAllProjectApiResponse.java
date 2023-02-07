package com.kshrd.hrdprojectcontrolapi.rest.response;

public class ProgressOfAllProjectApiResponse {

    private int projectId;

    private String projectName;

    private float projectProgress;

    private String generationName;

    private String courseName;

    private int numberOfIssue;

    public ProgressOfAllProjectApiResponse(int projectId, String projectName, float projectProgress, String generationName, String courseName, int numberOfIssue) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectProgress = projectProgress;
        this.generationName = generationName;
        this.courseName = courseName;
        this.numberOfIssue = numberOfIssue;
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

    public float getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(float projectProgress) {
        this.projectProgress = projectProgress;
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

    public int getNumberOfIssue() {
        return numberOfIssue;
    }

    public void setNumberOfIssue(int numberOfIssue) {
        this.numberOfIssue = numberOfIssue;
    }

    @Override
    public String toString() {
        return "ProgressOfAllProjectApiResponse{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectProgress=" + projectProgress +
                ", generationName='" + generationName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", numberOfIssue=" + numberOfIssue +
                '}';
    }
}
