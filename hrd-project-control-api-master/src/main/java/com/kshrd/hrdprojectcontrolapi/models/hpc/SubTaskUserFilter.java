package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class SubTaskUserFilter {

    private Integer projectId;

    private Integer handlerId;

    public SubTaskUserFilter() {}

    public SubTaskUserFilter(Integer projectId, Integer generationId, Integer courseId, Integer handlerId) {
        this.projectId = projectId;
        this.handlerId = handlerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

}
