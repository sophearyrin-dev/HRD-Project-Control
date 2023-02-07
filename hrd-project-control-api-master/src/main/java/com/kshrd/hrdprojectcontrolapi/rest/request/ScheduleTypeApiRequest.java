package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ScheduleTypeApiRequest {
    private String name;
    private int generationId;
    private int courseId;
    @JsonIgnore
    private int typeId;

    public ScheduleTypeApiRequest() {
    }

    public ScheduleTypeApiRequest(String name, int generationId, int courseId, int typeId) {
        this.name = name;
        this.generationId = generationId;
        this.courseId = courseId;
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ScheduleTypeApiRequest{" +
                "name='" + name + '\'' +
                ", generationId=" + generationId +
                ", courseId=" + courseId +
                ", typeId=" + typeId +
                '}';
    }
}
