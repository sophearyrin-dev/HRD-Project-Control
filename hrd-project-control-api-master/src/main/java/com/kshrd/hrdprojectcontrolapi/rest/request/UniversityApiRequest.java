package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UniversityApiRequest {
    @JsonIgnore
    private int universityId;
    private String name;

    public UniversityApiRequest() {
    }

    public UniversityApiRequest(int universityId, String name) {
        this.universityId = universityId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    @Override
    public String toString() {
        return "UniversityApiRequest{" +
                "universityId=" + universityId +
                ", name='" + name + '\'' +
                '}';
    }
}
