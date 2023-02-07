package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class TaskProgressFilter {

    private Integer projectId;

    public TaskProgressFilter() {}

    public TaskProgressFilter(Integer projectId, Integer generationId, Integer courseId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
