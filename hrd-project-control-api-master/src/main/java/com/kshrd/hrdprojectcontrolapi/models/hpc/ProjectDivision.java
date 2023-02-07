package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class ProjectDivision {

    private int projectId;

    private int userId;

    private int typeId;

    private int documentId;

    private int scheduleId;

    public ProjectDivision() {
    }

    public ProjectDivision(int projectId, int userId, int typeId, int documentId, int scheduleId) {
        this.projectId = projectId;
        this.userId = userId;
        this.typeId = typeId;
        this.documentId = documentId;
        this.scheduleId = scheduleId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ProjectDivision{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", documentId=" + documentId +
                ", scheduleId=" + scheduleId +
                '}';
    }
}
