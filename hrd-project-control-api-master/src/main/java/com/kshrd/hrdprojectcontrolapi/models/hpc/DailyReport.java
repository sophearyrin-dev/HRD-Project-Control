/*************************************************************
 * Author: Sot Sirymony
 * Create Date: july,28,2020
 * Developer Group: HRD PROJECT CONTROL
 * Description: using insert data
 *************************************************************/

package com.kshrd.hrdprojectcontrolapi.models.hpc;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Scanner;


public class DailyReport {

    /***********************************************************
     * TODO: insert data to db by class DailyReport
     **********************************************************/
    private int id;

    private String developerName;

    private String currentDate;

    private String mainTask;

    private String subTask;

    private String deadLine;

    private int progressPercentage;

    private boolean status;

    private String message;

    private int projectId;

    Project project;

    public DailyReport(){};

    public DailyReport(int id, String developerName, String currentDate, String mainTask, String subTask, String deadLine, int progressPercentage, boolean status, String message, int projectId, Project project) {

        this.id = id;

        this.developerName = developerName;

        this.currentDate = currentDate;

        this.mainTask = mainTask;

        this.subTask = subTask;

        this.deadLine = deadLine;

        this.progressPercentage = progressPercentage;

        this.status = status;

        this.message = message;

        this.projectId = projectId;

        this.project = project;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getDeveloperName() {

        return developerName;
    }

    public void setDeveloperName(String developerName) {

        this.developerName = developerName;
    }

    public String getCurrentDate() {

        return currentDate;
    }

    public void setCurrentDate(String currentDate) {

        this.currentDate = currentDate;
    }

    public String getMainTask() {

        return mainTask;
    }

    public void setMainTask(String mainTask) {

        this.mainTask = mainTask;
    }

    public String getSubTask() {

        return subTask;
    }

    public void setSubTask(String subTask) {

        this.subTask = subTask;
    }

    public String getDeadLine() {

        return deadLine;
    }

    public void setDeadLine(String deadLine) {

        this.deadLine = deadLine;
    }

    public int getProgressPercentage() {

        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {

        this.progressPercentage = progressPercentage;
    }

    public boolean isStatus() {

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

    public int getProjectId() {

        return projectId;
    }

    public void setProjectId(int projectId) {

        this.projectId = projectId;
    }

    public Project getProject() {

        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "id=" + id +
                ", developerName='" + developerName + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", mainTask='" + mainTask + '\'' +
                ", subTask='" + subTask + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", progressPercentage=" + progressPercentage +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", projectId=" + projectId +
                ", project=" + project +
                '}';
    }
}



