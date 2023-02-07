package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubTaskDeleteApiRequest {

    @JsonIgnore
    private int id;

    public SubTaskDeleteApiRequest() {}

    public SubTaskDeleteApiRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SubTaskDeleteApiRequest{" +
                "id=" + id +
                '}';
    }
}
