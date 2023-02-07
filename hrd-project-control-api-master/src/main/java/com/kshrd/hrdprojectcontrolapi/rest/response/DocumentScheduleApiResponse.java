package com.kshrd.hrdprojectcontrolapi.rest.response;

import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;

public class DocumentScheduleApiResponse {
    private int id;
    private ScheduleTypeApiResponse type;
    private Boolean status;

    public DocumentScheduleApiResponse() {
    }

    public DocumentScheduleApiResponse(int id, ScheduleTypeApiResponse type, Boolean status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduleTypeApiResponse getScheduleType() {
        return type;
    }

    public void setScheduleType(ScheduleTypeApiResponse type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DocumentScheduleApiResponse{" +
                "id=" + id +
                ", scheduleType=" + type +
                ", status=" + status +
                '}';
    }
}
