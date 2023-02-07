package com.kshrd.hrdprojectcontrolapi.rest.response;

public class CheckProject {
    int projectId;

    public CheckProject() {
    }

    public CheckProject(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "checkProject{" +
                "projectId=" + projectId +
                '}';
    }
}
