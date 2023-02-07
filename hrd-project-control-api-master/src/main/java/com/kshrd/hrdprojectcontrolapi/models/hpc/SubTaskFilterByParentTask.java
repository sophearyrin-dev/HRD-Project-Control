package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class SubTaskFilterByParentTask {

    private Integer parentTaskId;

    public SubTaskFilterByParentTask() {}

    public SubTaskFilterByParentTask(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
