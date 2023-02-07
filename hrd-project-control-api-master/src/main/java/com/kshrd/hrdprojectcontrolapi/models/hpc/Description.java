package com.kshrd.hrdprojectcontrolapi.models.hpc;

import java.sql.Timestamp;

public class Description {

    private String topic;

    private Timestamp startDate;

    private Timestamp endDate;

    private double percentage;


    public Description() {
    }

    public Description(String topic, Timestamp startDate, Timestamp endDate) {
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Description{" +
                "topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
