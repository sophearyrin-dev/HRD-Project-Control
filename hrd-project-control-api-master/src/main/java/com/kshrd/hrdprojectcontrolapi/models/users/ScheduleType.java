package com.kshrd.hrdprojectcontrolapi.models.users;

public class ScheduleType {
    private int typeId;
    private String name;
    private int generationId;
    private int courseId;
    private Boolean status;

    public ScheduleType() {
    }

    public ScheduleType(int typeId, String name, int generationId, int courseId, Boolean status) {
        this.typeId = typeId;
        this.name = name;
        this.generationId = generationId;
        this.courseId = courseId;
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

    @Override
    public String toString() {
        return "ScheduleType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                ", generationId=" + generationId +
                ", courseId=" + courseId +
                ", status=" + status +
                '}';
    }
}
