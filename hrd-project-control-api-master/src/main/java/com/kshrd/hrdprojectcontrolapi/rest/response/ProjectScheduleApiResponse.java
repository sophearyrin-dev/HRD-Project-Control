package com.kshrd.hrdprojectcontrolapi.rest.response;
public class ProjectScheduleApiResponse {
    private int id;
    private String groupMeeting;
    private Boolean status=true;
    private ScheduleTypeApiResponse type;

    public ProjectScheduleApiResponse() {
    }

    public ProjectScheduleApiResponse(int id, String groupMeeting, Boolean status, ScheduleTypeApiResponse type) {
        this.id = id;
        this.groupMeeting = groupMeeting;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ScheduleTypeApiResponse getType() {
        return type;
    }

    public void setType(ScheduleTypeApiResponse type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProjectScheduleApiResponse{" +
                "id=" + id +
                ", groupMeeting='" + groupMeeting + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
