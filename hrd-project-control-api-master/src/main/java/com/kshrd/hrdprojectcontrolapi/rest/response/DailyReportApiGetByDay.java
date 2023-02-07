package com.kshrd.hrdprojectcontrolapi.rest.response;

import java.sql.Timestamp;

public class DailyReportApiGetByDay {
    private String currentDate;
    private String developerName;
    private String mainTask;
    private String subTask;
    private String deadLine;
    private int progressPercentage;
    private boolean status;
    private String message;
    public DailyReportApiGetByDay(){};

    public DailyReportApiGetByDay( String currentDate, String developerName, String mainTask, String subTask, String deadLine, int progressPercentage, boolean status, String message) {
        this.currentDate = currentDate;
        this.developerName = developerName;
        this.mainTask = mainTask;
        this.subTask = subTask;
        this.deadLine = deadLine;
        this.progressPercentage = progressPercentage;
        this.status = status;
        this.message = message;
    }



    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
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

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        return "DailyReportApiGetByDay{" +

                ", currentDate='" + currentDate + '\'' +
                ", developerName='" + developerName + '\'' +
                ", mainTask='" + mainTask + '\'' +
                ", subTask='" + subTask + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", progressPercentage=" + progressPercentage +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
