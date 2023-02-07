package com.kshrd.hrdprojectcontrolapi.rest.response;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyReportInsertResponse {
    private int id;

    private String developer_name;

    private String current_date;

    private String main_task;

    private String sub_task;

    private String deadline;

    private int progress_percentage;

    private boolean status;

    private String message;

    private int project_id;

    Project project;

    public DailyReportInsertResponse(){};

    public DailyReportInsertResponse(int id, String developer_name, String current_date, String main_task, String sub_task, String deadline, int progress_percentage, boolean status, String message, int project_id, Project project) {
        this.id = id;
        this.developer_name = developer_name;
        this.current_date = current_date;
        this.main_task = main_task;
        this.sub_task = sub_task;
        this.deadline = deadline;
        this.progress_percentage = progress_percentage;
        this.status = status;
        this.message = message;
        this.project_id = project_id;
        this.project = project;
    }

    public Date getDeadline() {

        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(deadline);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public Date getCurrent_date() {

        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(current_date);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getDeveloper_name() {

        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {

        this.developer_name = developer_name;
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

    public void setDeadline(String deadline) {

        this.deadline = deadline;
    }

    public int getProgress_percentage() {

        return progress_percentage;
    }

    public void setProgress_percentage(int progress_percentage) {

        this.progress_percentage = progress_percentage;
    }

    public boolean getStatus() {

        return status;
    }

    public void setStatus(boolean status) {

        this.status = status;
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

    public Project getProject() {

        return project;
    }

    public void setProject(Project project) {

        this.project = project;
    }
}
