package com.kshrd.hrdprojectcontrolapi.models.hpc;

import java.sql.Timestamp;
import java.util.List;

public class Document {
    private Integer documentId;

    private String type;

    private List<Description> descriptions;

    private Timestamp meeting;

    private String status;

    public Document() {
    }

    public Document(Integer scheduleId, String type, List<Description> descriptions, Timestamp meeting, String status) {
        this.documentId = scheduleId;
        this.type = type;
        this.descriptions = descriptions;
        this.meeting = meeting;
        this.status = status;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Timestamp getMeeting() {
        return meeting;
    }

    public void setMeeting(Timestamp meeting) {
        this.meeting = meeting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "documentId=" + documentId +
                ", type='" + type + '\'' +
                ", descriptions=" + descriptions +
                ", meeting=" + meeting +
                ", status='" + status + '\'' +
                '}';
    }
}
