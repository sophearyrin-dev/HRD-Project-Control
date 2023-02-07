package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DailyReportApiRequest {
    private String developer_name;
    @JsonFormat(pattern = "YYYY-MM-DD",shape = JsonFormat.Shape.STRING)
    private String current_date;
    private String main_task;
    private String sub_task;
    @JsonFormat(pattern = "YYYY-MM-DD",shape = JsonFormat.Shape.STRING)
    private String deadline;
    private int progress_percentage;
    private String message;
    private int project_id;
    DailyReportApiRequest(){};

    public DailyReportApiRequest(String developer_name, String current_date, String main_task, String sub_task, String deadline, int progress_percentage, String message, int project_id) {
        this.developer_name = developer_name;
        this.current_date = current_date;
        this.main_task = main_task;
        this.sub_task = sub_task;
        this.deadline = deadline;
        this.progress_percentage = progress_percentage;
        this.message = message;
        this.project_id = project_id;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getMain_task() {
        return main_task;
    }

    public void setMain_task(String main_task) {
        this.main_task = main_task;
    }

    public String getSub_task() {
        return sub_task;
    }

    public void setSub_task(String sub_task) {
        this.sub_task = sub_task;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getProgress_percentage() {
        return progress_percentage;
    }

    public void setProgress_percentage(int progress_percentage) {
        this.progress_percentage = progress_percentage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}