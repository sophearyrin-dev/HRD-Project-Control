package com.kshrd.hrdprojectcontrolapi.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ProjectDivisionApiResponse {

    private int id;

    private UserApiResponse user;

    public ProjectDivisionApiResponse() {
    }

    public ProjectDivisionApiResponse(int id, UserApiResponse user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserApiResponse getUser() {
        return user;
    }

    public void setUser(UserApiResponse user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProjectDivisionApiResponse{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }

//    private int id;
//
//    private int projectId;
//
//    private List<UserApiResponse> userApiResponses;
//
//    public ProjectDivisionApiResponse() {
//    }
//
//    public ProjectDivisionApiResponse(int id, int projectId, List<UserApiResponse> userApiResponses) {
//        this.id = id;
//        this.projectId = projectId;
//        this.userApiResponses = userApiResponses;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(int projectId) {
//        this.projectId = projectId;
//    }
//
//    public List<UserApiResponse> getUserApiResponses() {
//        return userApiResponses;
//    }
//
//    public void setUserApiResponses(List<UserApiResponse> userApiResponses) {
//        this.userApiResponses = userApiResponses;
//    }
//
//    @Override
//    public String toString() {
//        return "ProjectDivisionApiResponse{" +
//                "id=" + id +
//                ", projectId=" + projectId +
//                ", userApiResponses=" + userApiResponses +
//                '}';
//    }
}
