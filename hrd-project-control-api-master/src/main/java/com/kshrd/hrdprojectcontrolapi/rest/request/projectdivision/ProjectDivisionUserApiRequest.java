package com.kshrd.hrdprojectcontrolapi.rest.request.projectdivision;

public class ProjectDivisionUserApiRequest {

    int userId;

    public ProjectDivisionUserApiRequest() {
    }

    public ProjectDivisionUserApiRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProjectDivisionUserApiRequest{" +
                "userId=" + userId +
                '}';
    }
}
