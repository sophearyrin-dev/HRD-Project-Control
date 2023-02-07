package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectScheduleApiRequst {
    @JsonIgnore
    private int id;

    private int typeId;
    private String groupMeeting;
    @JsonIgnore
    private Boolean status=true;

    public ProjectScheduleApiRequst() {
    }

    public ProjectScheduleApiRequst(int id, int typeId, String groupMeeting, Boolean status) {
        this.id = id;
        this.typeId = typeId;
        this.groupMeeting = groupMeeting;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getGroupMeeting() {
        return groupMeeting;
    }

    public void setGroupMeeting(String groupMeeting) {
        this.groupMeeting = groupMeeting;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectScheduleApiRequst{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", groupMeeting='" + groupMeeting + '\'' +
                ", status=" + status +
                '}';
    }
}
