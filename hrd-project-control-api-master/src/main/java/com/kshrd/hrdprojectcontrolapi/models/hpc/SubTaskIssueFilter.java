package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class SubTaskIssueFilter {

    private Integer projectId;

    public SubTaskIssueFilter() {}

    public SubTaskIssueFilter(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
