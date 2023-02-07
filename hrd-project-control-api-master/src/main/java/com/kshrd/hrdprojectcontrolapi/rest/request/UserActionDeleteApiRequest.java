package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserActionDeleteApiRequest {

    @JsonIgnore
    private int id;
    private Boolean status;

    public UserActionDeleteApiRequest() {
    }

    public UserActionDeleteApiRequest(int id, Boolean status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserActionDelete{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
