package com.kshrd.hrdprojectcontrolapi.models.hpc;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentTopic {
    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String endDate;
    private float percentage;
    private int projectScheduleId;
    private Boolean status;

    public DocumentTopic() {
    }

    public DocumentTopic(int id, String name, String startDate, String endDate, float percentage, int projectScheduleId, Boolean status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.projectScheduleId = projectScheduleId;
        this.status = status;
    }

    public int getProjectScheduleId() {
        return projectScheduleId;
    }

    public void setProjectScheduleId(int projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProjectId() {
        return projectScheduleId;
    }

    public void setProjectId(int projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

    @Override
    public String toString() {
        return "DocumentTopic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage=" + percentage +
                ", projectScheduleId=" + projectScheduleId +
                ", status=" + status +
                '}';
    }
}
