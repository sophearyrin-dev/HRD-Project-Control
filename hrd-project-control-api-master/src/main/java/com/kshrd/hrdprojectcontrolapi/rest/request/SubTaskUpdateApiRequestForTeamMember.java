package com.kshrd.hrdprojectcontrolapi.rest.request;

public class SubTaskUpdateApiRequestForTeamMember {

    private int percentage;

    private String issue;

    private String message;

    public SubTaskUpdateApiRequestForTeamMember() {}

    public SubTaskUpdateApiRequestForTeamMember(int percentage, String issue, String message) {
        this.percentage = percentage;
        this.issue = issue;
        this.message = message;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
}
