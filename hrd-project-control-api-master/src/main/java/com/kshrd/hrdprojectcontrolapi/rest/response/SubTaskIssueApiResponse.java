package com.kshrd.hrdprojectcontrolapi.rest.response;

public class SubTaskIssueApiResponse {

    private int subTaskId;

    private String handler;

    private String issue;

    private String message;

    public SubTaskIssueApiResponse() {}

    public SubTaskIssueApiResponse(int subTaskId, String handler, String issue, String message) {
        this.subTaskId = subTaskId;
        this.handler = handler;
        this.issue = issue;
        this.message = message;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

    @Override
    public String toString() {
        return "SubTaskIssueApiResponse{" +
                "subTaskId='" + subTaskId + '\'' +
                "handler='" + handler + '\'' +
                "issue='" + issue + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
