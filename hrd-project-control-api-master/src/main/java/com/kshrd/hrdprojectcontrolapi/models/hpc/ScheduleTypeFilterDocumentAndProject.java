package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class ScheduleTypeFilterDocumentAndProject {

    private int projectScheduleId;

    private int documentScheduleId;

    public ScheduleTypeFilterDocumentAndProject() {
    }

    public ScheduleTypeFilterDocumentAndProject(int projectScheduleId, int documentScheduleId) {
        this.projectScheduleId = projectScheduleId;
        this.documentScheduleId = documentScheduleId;
    }

    public int getProjectScheduleId() {
        return projectScheduleId;
    }

    public void setProjectScheduleId(int projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

    public int getDocumentScheduleId() {
        return documentScheduleId;
    }

    public void setDocumentScheduleId(int documentScheduleId) {
        this.documentScheduleId = documentScheduleId;
    }

    @Override
    public String toString() {
        return "ScheduleTypeFilterDocumentAndProject{" +
                "projectScheduleId=" + projectScheduleId +
                ", documentScheduleId=" + documentScheduleId +
                '}';
    }
}
