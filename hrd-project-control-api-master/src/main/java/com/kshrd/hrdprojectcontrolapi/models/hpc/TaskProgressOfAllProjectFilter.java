package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class TaskProgressOfAllProjectFilter {

    private Integer generationId;

    private Integer courseId;

    public TaskProgressOfAllProjectFilter() {}

    public TaskProgressOfAllProjectFilter(Integer generationId, Integer courseId, Integer projectScheduleId, Integer scheduleTypeId) {
        this.generationId = generationId;
        this.courseId = courseId;
    }

    public Integer getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Integer generationId) {
        this.generationId = generationId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
