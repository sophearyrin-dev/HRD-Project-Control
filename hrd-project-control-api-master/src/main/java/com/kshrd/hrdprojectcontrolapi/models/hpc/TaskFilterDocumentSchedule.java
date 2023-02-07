package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class TaskFilterDocumentSchedule {

    private Integer projectId;

    private Integer scheduleTypeId;

    private Integer documentScheduleId;

    public TaskFilterDocumentSchedule() {}

    public TaskFilterDocumentSchedule(Integer projectId, Integer scheduleTypeId, Integer documentScheduleId) {
        this.projectId = projectId;
        this.scheduleTypeId = scheduleTypeId;
        this.documentScheduleId = documentScheduleId;
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

    public Integer getDocumentScheduleId() {
        return documentScheduleId;
    }

    public void setDocumentScheduleId(Integer documentScheduleId) {
        this.documentScheduleId = documentScheduleId;
    }
}
