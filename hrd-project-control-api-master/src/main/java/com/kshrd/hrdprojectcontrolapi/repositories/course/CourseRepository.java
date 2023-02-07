package com.kshrd.hrdprojectcontrolapi.repositories.course;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Course;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository {

    @SelectProvider(value = CourseProvider.class, method = "findAllCourse")
    @Results({
            @Result(column = "id", property = "courseId"),
            @Result(column = "name", property = "name")
    })
    List<Course> findAllCourse();
}
