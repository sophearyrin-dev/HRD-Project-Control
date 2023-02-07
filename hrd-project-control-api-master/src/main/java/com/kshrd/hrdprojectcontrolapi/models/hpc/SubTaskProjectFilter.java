package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class SubTaskProjectFilter {

    private Integer projectId;

    public SubTaskProjectFilter() {}

    public SubTaskProjectFilter(Integer projectId, Integer generationId, Integer courseId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
