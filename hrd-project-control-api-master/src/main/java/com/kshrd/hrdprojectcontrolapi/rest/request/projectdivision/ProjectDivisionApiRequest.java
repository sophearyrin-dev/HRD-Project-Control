package com.kshrd.hrdprojectcontrolapi.rest.request.projectdivision;

import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;

import java.util.List;

public class ProjectDivisionApiRequest {

    private int projectId;

    private int scheduleTypeId;

    private List<Integer> projectDivisionUserApiRequests;

//    private int

    public ProjectDivisionApiRequest() {
    }

    public ProjectDivisionApiRequest(int projectId, int scheduleTypeId, List<Integer> projectDivisionUserApiRequests) {
        this.projectId = projectId;
        this.scheduleTypeId = scheduleTypeId;
        this.projectDivisionUserApiRequests = projectDivisionUserApiRequests;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getScheduleTypeId() {
        return scheduleTypeId;
    }

    public void setScheduleTypeId(int scheduleTypeId) {
        this.scheduleTypeId = scheduleTypeId;
    }

    public List<Integer> getProjectDivisionUserApiRequests() {
        return projectDivisionUserApiRequests;
    }

    public void setProjectDivisionUserApiRequests(List<Integer> projectDivisionUserApiRequests) {
        this.projectDivisionUserApiRequests = projectDivisionUserApiRequests;
    }

    @Override
    public String toString() {
        return "ProjectDivisionApiRequest{" +
                "projectId=" + projectId +
                ", scheduleTypeId=" + scheduleTypeId +
                ", projectDivisionUserApiRequests=" + projectDivisionUserApiRequests +
                '}';
    }
}
