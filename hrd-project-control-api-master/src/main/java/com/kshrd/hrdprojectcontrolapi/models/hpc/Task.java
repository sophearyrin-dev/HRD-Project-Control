package com.kshrd.hrdprojectcontrolapi.models.hpc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kshrd.hrdprojectcontrolapi.models.users.User;

public class Task {

    private int id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String startDate;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String endDate;

    private float percentage;

    User user;

    Project project;

    private int parentTaskId;

    private char priority;

    private String issue;

    public Task() {}

    public Task(int id, String name, String startDate, String endDate, float percentage, User user, Project project, int parentTaskId, char priority, String issue) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.user = user;
        this.project = project;
        this.parentTaskId = parentTaskId;
        this.priority = priority;
        this.issue = issue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
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

    @Override
    public String toString() {
        return "task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage=" + percentage +
                ", user=" + user +
                ", project=" + project +
                ", parentTaskId=" + parentTaskId +
                ", priority=" + priority +
                ", issue='" + issue + '\'' +
                '}';
    }
}
