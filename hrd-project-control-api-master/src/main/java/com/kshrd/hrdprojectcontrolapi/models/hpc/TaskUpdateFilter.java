package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class TaskUpdateFilter {

    private Integer parentTaskId;

    public TaskUpdateFilter() {}

    public TaskUpdateFilter(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
