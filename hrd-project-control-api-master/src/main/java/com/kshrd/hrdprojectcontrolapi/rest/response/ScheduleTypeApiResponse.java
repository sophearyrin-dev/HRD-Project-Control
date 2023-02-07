package com.kshrd.hrdprojectcontrolapi.rest.response;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Course;
import com.kshrd.hrdprojectcontrolapi.models.users.Generation;

public class ScheduleTypeApiResponse {
    private int typeId;
    private String name;
    private Generation generation;
    private Course course;
    private Boolean status;

    public ScheduleTypeApiResponse() {
    }

    public ScheduleTypeApiResponse(int typeId, String name, Generation generation, Course course, Boolean status) {
        this.typeId = typeId;
        this.name = name;
        this.generation = generation;
        this.course = course;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ScheduleTypeApiResponse{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                ", generation=" + generation +
                ", course=" + course +
                ", status=" + status +
                '}';
    }
}
