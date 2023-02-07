package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DocumentScheduleApiRequest {

    @JsonIgnore
    private int id;

    private int typeId;

    @JsonIgnore
    private Boolean status;

    public DocumentScheduleApiRequest() {
    }

    public DocumentScheduleApiRequest(int id, int typeId, Boolean status) {
        this.id = id;
        this.typeId = typeId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DocumentScheduleApiRequest{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", status=" + status +
                '}';
    }
}
