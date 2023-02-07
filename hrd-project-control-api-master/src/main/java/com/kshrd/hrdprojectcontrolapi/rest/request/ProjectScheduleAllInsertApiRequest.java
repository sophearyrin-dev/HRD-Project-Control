package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectScheduleApiResponse;

import java.util.List;

public class ProjectScheduleAllInsertApiRequest {
    private ProjectScheduleApiRequst projectScheduleApiRequst;
    private List<ProjectTopicInsertAllApiRequest> projectTopicApiRequestList;

    public ProjectScheduleAllInsertApiRequest() {
    }

    public ProjectScheduleAllInsertApiRequest(ProjectScheduleApiRequst projectScheduleApiRequst, List<ProjectTopicInsertAllApiRequest> projectTopicApiRequestList) {
        this.projectScheduleApiRequst = projectScheduleApiRequst;
        this.projectTopicApiRequestList = projectTopicApiRequestList;
    }

    public ProjectScheduleApiRequst getProjectScheduleApiRequst() {
        return projectScheduleApiRequst;
    }

    public void setProjectScheduleApiRequst(ProjectScheduleApiRequst projectScheduleApiRequst) {
        this.projectScheduleApiRequst = projectScheduleApiRequst;
    }

    public List<ProjectTopicInsertAllApiRequest> getProjectTopicApiRequestList() {
        return projectTopicApiRequestList;
    }

    public void setProjectTopicApiRequestList(List<ProjectTopicInsertAllApiRequest> projectTopicApiRequestList) {
        this.projectTopicApiRequestList = projectTopicApiRequestList;
    }

    @Override
    public String toString() {
        return "ProjectScheduleAllInsertApiRequest{" +
                "projectScheduleApiRequst=" + projectScheduleApiRequst +
                ", projectTopicApiRequestList=" + projectTopicApiRequestList +
                '}';
    }
}
