package com.kshrd.hrdprojectcontrolapi.rest.response;

public class TaskApiResponse {

    private int id;

    private String mainTaskName;

    private String startDate;

    private String endDate;

    public TaskApiResponse() {}

    public TaskApiResponse(int id, String mainTaskName, String startDate, String endDate) {
        this.id = id;
        this.mainTaskName = mainTaskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainTaskName() {
        return mainTaskName;
    }

    public void setMainTaskName(String mainTaskName) {
        this.mainTaskName = mainTaskName;
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
        return "TaskApiResponse{" +
                "id=" + id +
                ", mainTaskName='" + mainTaskName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
