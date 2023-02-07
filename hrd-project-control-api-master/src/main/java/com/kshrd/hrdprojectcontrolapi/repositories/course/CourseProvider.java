package com.kshrd.hrdprojectcontrolapi.repositories.course;

import org.apache.ibatis.jdbc.SQL;

public class CourseProvider {

    public String findAllCourse() {
        return new SQL() {{
            SELECT("*");
            FROM("course");
        }}.toString();
    }
}
