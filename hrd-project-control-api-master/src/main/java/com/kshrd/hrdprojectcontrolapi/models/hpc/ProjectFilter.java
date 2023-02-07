/*************************************************************
 * Author: Sot Sirymony
 * Create Date: july,28,2020
 * Developer Group: HRD PROJECT CONTROL
 * Description: using filter
 *************************************************************/

package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class ProjectFilter {

    Integer generation_id;

    Integer course_id;

    public ProjectFilter(){};

    public ProjectFilter(Integer generation_id, Integer course_id) {

        this.generation_id = generation_id;

        this.course_id = course_id;
    }

    public Integer getGeneration_id() {
        return generation_id;
    }

    public void setGeneration_id(Integer generation_id) {
        this.generation_id = generation_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {

        return "ProjectFilter{" +
                "generation_id=" + generation_id +
                ", course_id=" + course_id +
                '}';
    }
}

