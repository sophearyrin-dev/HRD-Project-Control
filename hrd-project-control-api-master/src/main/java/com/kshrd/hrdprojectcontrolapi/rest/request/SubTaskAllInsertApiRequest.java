package com.kshrd.hrdprojectcontrolapi.rest.request;

import java.util.List;

public class SubTaskAllInsertApiRequest {

    private int projectId;

    private int parentTaskId;

    List<SubTaskApiRequest> subTaskApiRequests;

    public SubTaskAllInsertApiRequest() {}

    public SubTaskAllInsertApiRequest(int projectId, int parentTaskId, List<SubTaskApiRequest> subTaskApiRequests) {
        this.projectId = projectId;
        this.parentTaskId = parentTaskId;
        this.subTaskApiRequests = subTaskApiRequests;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public List<SubTaskApiRequest> getSubTaskApiRequests() {
        return subTaskApiRequests;
    }

    public void setSubTaskApiRequests(List<SubTaskApiRequest> subTaskApiRequests) {
        this.subTaskApiRequests = subTaskApiRequests;
    }
}
