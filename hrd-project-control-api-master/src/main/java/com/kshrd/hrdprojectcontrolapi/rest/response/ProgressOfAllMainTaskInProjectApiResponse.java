package com.kshrd.hrdprojectcontrolapi.rest.response;

public class ProgressOfAllMainTaskInProjectApiResponse {

    private int parentTaskId;

    private String parentTaskName;

    private float percentage;

    private String startDate;

    private String endDate;

    private int numberOfIssue;

    public ProgressOfAllMainTaskInProjectApiResponse() {}

    public ProgressOfAllMainTaskInProjectApiResponse(int parentTaskId, String parentTaskName, float percentage, String startDate, String endDate, int numberOfIssue) {
        this.parentTaskId = parentTaskId;
        this.parentTaskName = parentTaskName;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfIssue = numberOfIssue;
    }

    public int getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfIssue() {
        return numberOfIssue;
    }

    public void setNumberOfIssue(int numberOfIssue) {
        this.numberOfIssue = numberOfIssue;
    }

    @Override
    public String toString() {
        return "ProgressOfAllMainTaskInProjectApiResponse{" +
                "parentTaskId=" + parentTaskId +
                ", parentTaskName='" + parentTaskName + '\'' +
                ", percentage=" + percentage +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numberOfIssue='" + numberOfIssue + '\'' +
                '}';
    }
}
