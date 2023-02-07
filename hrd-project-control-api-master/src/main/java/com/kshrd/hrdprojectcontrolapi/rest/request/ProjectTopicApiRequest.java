package com.kshrd.hrdprojectcontrolapi.rest.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectTopicApiRequest {
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String endDate;
    private float percentage;
    @JsonIgnore
    private int projectScheduleId;
    @JsonIgnore
    private Boolean status;
    @JsonIgnore
    private int id;

    public ProjectTopicApiRequest() {
    }

    public ProjectTopicApiRequest(String name, String startDate, String endDate, float percentage, int projectScheduleId, Boolean status, int id) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.projectScheduleId = projectScheduleId;
        this.status = status;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(startDate);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate(){
        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(endDate);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getProjectScheduleId() {
        return projectScheduleId;
    }

    public void setProjectScheduleId(int projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

    @Override
    public String toString() {
        return "ProjectTopicApiRequest{" +
                "name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage=" + percentage +
                ", projectScheduleId=" + projectScheduleId +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
