package com.kshrd.hrdprojectcontrolapi.services.course;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Course;
import com.kshrd.hrdprojectcontrolapi.repositories.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAllCourse();
    }
}
