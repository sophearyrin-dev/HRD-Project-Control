package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class TaskFilterProjectSchedule {

    private Integer projectId;

    private Integer scheduleTypeId;

    private Integer projectScheduleId;

    public TaskFilterProjectSchedule() {}

    public TaskFilterProjectSchedule(Integer projectId, Integer scheduleTypeId, Integer projectScheduleId) {
        this.projectId = projectId;
        this.scheduleTypeId = scheduleTypeId;
        this.projectScheduleId = projectScheduleId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getScheduleTypeId() {
        return scheduleTypeId;
    }

    public void setScheduleTypeId(Integer scheduleTypeId) {
        this.scheduleTypeId = scheduleTypeId;
    }

    public Integer getProjectScheduleId() {
        return projectScheduleId;
    }

    public void setProjectScheduleId(Integer projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

}
