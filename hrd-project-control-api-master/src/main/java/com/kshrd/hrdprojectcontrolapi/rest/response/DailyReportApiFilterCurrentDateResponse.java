package com.kshrd.hrdprojectcontrolapi.rest.response;

import java.sql.Timestamp;

public class DailyReportApiFilterCurrentDateResponse {
    private int Id;
    private Timestamp currentDate;
    private String developerName;
    private String mainTask;
    private String subTask;
    private String deadline;
    private int progressPercentage;
    private Boolean status;
    private String message;
    public DailyReportApiFilterCurrentDateResponse(){}

    public DailyReportApiFilterCurrentDateResponse(int id, Timestamp currentDate, String developerName, String mainTask, String subTask, String deadline, int progressPercentage, Boolean status, String message) {
        Id = id;
        this.currentDate = currentDate;
        this.developerName = developerName;
        this.mainTask = mainTask;
        this.subTask = subTask;
        this.deadline = deadline;
        this.progressPercentage = progressPercentage;
        this.status = status;
        this.message = message;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Timestamp getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Timestamp currentDate) {
        this.currentDate = currentDate;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getMainTask() {
        return mainTask;
    }

    public void setMainTask(String mainTask) {
        this.mainTask = mainTask;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DailyReportApiFilterCurrentDateResponse{" +
                "Id=" + Id +
                ", currentDate=" + currentDate +
                ", developerName='" + developerName + '\'' +
                ", mainTask='" + mainTask + '\'' +
                ", subTask='" + subTask + '\'' +
                ", deadline='" + deadline + '\'' +
                ", progressPercentage=" + progressPercentage +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
