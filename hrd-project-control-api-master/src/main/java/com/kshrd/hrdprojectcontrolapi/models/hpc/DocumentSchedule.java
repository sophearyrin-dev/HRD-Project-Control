package com.kshrd.hrdprojectcontrolapi.models.hpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class DocumentSchedule {

    private int id;
    private int typeId;
    private Boolean status;

    public DocumentSchedule() {
    }

    public DocumentSchedule(int id, int typeId, Boolean status) {
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
        return "DocumentSchedule{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", status=" + status +
                '}';
    }
}
