package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class SubTaskCountIssueFilter {

    private Integer projectId;

    public SubTaskCountIssueFilter() {}

    public SubTaskCountIssueFilter(Integer projectId, Integer generationId, Integer courseId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
