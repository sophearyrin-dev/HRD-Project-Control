package com.kshrd.hrdprojectcontrolapi.models.users;


public class University {

    private int universityId;
    private String name;
    private Boolean status;
    public University() {
    }

    public University(int universityId, String name, Boolean status) {
        this.universityId = universityId;
        this.name = name;
        this.status = status;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

