package com.kshrd.hrdprojectcontrolapi.services.course;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Course;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;

import java.util.List;

public interface CourseService {

    List<Course> findAllCourse();

}
