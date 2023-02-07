package com.kshrd.hrdprojectcontrolapi.rest.response;

public class ProjectUserApiResponse {

    private Integer projectId;

    private String name;
    private int generationId;
    private int courseId;
    private String generation;
    private String course;
    private int projectScheduleId;
    private int documentScheduleId;

    public ProjectUserApiResponse() {
    }

    public ProjectUserApiResponse(Integer projectId, String name, int generationId, int courseId, String generation, String course, int projectScheduleId, int documentScheduleId) {
        this.projectId = projectId;
        this.name = name;
        this.generationId = generationId;
        this.courseId = courseId;
        this.generation = generation;
        this.course = course;
        this.projectScheduleId = projectScheduleId;
        this.documentScheduleId = documentScheduleId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getProjectScheduleId() {
        return projectScheduleId;
    }

    public void setProjectScheduleId(int projectScheduleId) {
        this.projectScheduleId = projectScheduleId;
    }

    public int getDocumentScheduleId() {
        return documentScheduleId;
    }

    public void setDocumentScheduleId(int documentScheduleId) {
        this.documentScheduleId = documentScheduleId;
    }

    @Override
    public String toString() {
        return "ProjectUserApiResponse{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", generationId=" + generationId +
                ", courseId=" + courseId +
                ", generation='" + generation + '\'' +
                ", course='" + course + '\'' +
                ", projectScheduleId=" + projectScheduleId +
                ", documentScheduleId=" + documentScheduleId +
                '}';
    }
}
