package com.kshrd.hrdprojectcontrolapi.models.hpc;

import java.sql.Timestamp;
import java.util.List;

public class Schedule {

    private Integer scheduleId;

    private String type;

    private List<Description> descriptions;

    private Timestamp meeting;

    private String status;

    public Schedule() {
    }

    public Schedule(Integer scheduleId, String type, List<Description> descriptions, Timestamp meeting, String status) {
        this.scheduleId = scheduleId;
        this.type = type;
        this.descriptions = descriptions;
        this.meeting = meeting;
        this.status = status;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Timestamp getMeeting() {
        return meeting;
    }

    public void setMeeting(Timestamp meeting) {
        this.meeting = meeting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", type='" + type + '\'' +
                ", descriptions=" + descriptions +
                ", meeting=" + meeting +
                ", status='" + status + '\'' +
                '}';
    }
}
