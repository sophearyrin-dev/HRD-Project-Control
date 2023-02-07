package com.kshrd.hrdprojectcontrolapi.rest.response;

public class CountIssueInProjectApiResponse {

    private int numberOfIssue;

    public CountIssueInProjectApiResponse() {}

    public CountIssueInProjectApiResponse(int numberOfIssue) {
        this.numberOfIssue = numberOfIssue;
    }

    public int getNumberOfIssue() {
        return numberOfIssue;
    }

    public void setNumberOfIssue(int numberOfIssue) {
        this.numberOfIssue = numberOfIssue;
    }
}
