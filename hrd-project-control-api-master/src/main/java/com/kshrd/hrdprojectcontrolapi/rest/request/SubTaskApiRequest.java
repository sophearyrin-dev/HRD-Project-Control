package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubTaskApiRequest {

    private String subTaskName;

    private int percentage;

    private int handlerId;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String startDate;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String endDate;;

    private char priority;

    public SubTaskApiRequest() {}

    public SubTaskApiRequest(String subTaskName, int percentage, int handlerId, String startDate, String endDate, char priority) {
        this.subTaskName = subTaskName;
        this.percentage = percentage;
        this.handlerId = handlerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
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

    public Date getEndDate() {
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

    public char getPriority() {
        return priority;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }
}
