package com.kshrd.hrdprojectcontrolapi.rest.response;

public class ProjectMainTaskApiResponse {

    private String taskName;

    private String startDate;

    private String endDate;

    public ProjectMainTaskApiResponse() {}

    public ProjectMainTaskApiResponse(String taskName, String startDate, String endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    @Override
    public String toString() {
        return "ProjectMainTaskApiResponse{" +
                "taskName='" + taskName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
