package com.kshrd.hrdprojectcontrolapi.models.hpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;

public class ProjectSchedule {

    private int id;
    private int typeId;
    private String groupMeeting;
    private Boolean status=true;

    public ProjectSchedule() {
    }

    public ProjectSchedule(int id, int typeId, String groupMeeting, Boolean status) {
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
        return "ProjectSchedule{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", groupMeeting='" + groupMeeting + '\'' +
                ", status=" + status +
                '}';
    }
}
