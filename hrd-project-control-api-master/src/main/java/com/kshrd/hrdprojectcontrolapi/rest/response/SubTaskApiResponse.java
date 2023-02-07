package com.kshrd.hrdprojectcontrolapi.rest.response;

public class SubTaskApiResponse {

    private int subTaskId;
    private String subTaskName;
    private int handlerId;
    private String handler;
    private String startDate;
    private String endDate;
    private float percentage;
    private char priority;
    private String issue;
    private String message;
    private String mainTaskName;

    public SubTaskApiResponse() {}

    public SubTaskApiResponse(int subTaskId, String subTaskName, int handlerId, String handler, String startDate, String endDate, float percentage, char priority, String issue, String message, String mainTaskName) {
        this.subTaskId = subTaskId;
        this.subTaskName = subTaskName;
        this.handlerId = handlerId;
        this.handler = handler;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.priority = priority;
        this.issue = issue;
        this.message = message;
        this.mainTaskName = mainTaskName;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public char getPriority() {
        return priority;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMainTaskName() {
        return mainTaskName;
    }

    public void setMainTaskName(String mainTaskName) {
        this.mainTaskName = mainTaskName;
    }

    @Override
    public String toString() {
        return "SubTaskApiResponse{" +
                "subTaskId='" + subTaskId + '\'' +
                "subTaskName='" + subTaskName + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", handler='" + handler + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage=" + percentage +
                ", priority=" + priority +
                ", issue='" + issue + '\'' +
                ", message='" + message + '\'' +
                ", mainTaskName='" + mainTaskName + '\'' +
                '}';
    }
}
