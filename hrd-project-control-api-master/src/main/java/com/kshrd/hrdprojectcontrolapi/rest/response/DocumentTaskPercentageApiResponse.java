package com.kshrd.hrdprojectcontrolapi.rest.response;

public class DocumentTaskPercentageApiResponse {

    private int projectId;

    private float documentTaskPercentage;

    public DocumentTaskPercentageApiResponse() {}

    public DocumentTaskPercentageApiResponse(int projectId, float documentTaskPercentage) {
        this.projectId = projectId;
        this.documentTaskPercentage = documentTaskPercentage;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public float getDocumentTaskPercentage() {
        return documentTaskPercentage;
    }

    public void setDocumentTaskPercentage(float documentTaskPercentage) {
        this.documentTaskPercentage = documentTaskPercentage;
    }

    @Override
    public String toString() {
        return "DocumentTaskPercentageApiResponse{" +
                "projectId=" + projectId +
                ", documentTaskPercentage=" + documentTaskPercentage +
                '}';
    }
}
