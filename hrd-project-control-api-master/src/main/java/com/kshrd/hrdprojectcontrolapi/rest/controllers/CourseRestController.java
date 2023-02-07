package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Course;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.course.CourseServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.List;

/**
 * This rest controller uses for get all courses(Basic and Advance course)
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/courses")
@Api(tags = "Course", value = "Course", description = "Controller for course control")
public class CourseRestController {

    private CourseServiceImp courseServiceImp;

    private MessageProperties message;

    @Autowired
    public CourseRestController(CourseServiceImp courseServiceImp) {
        this.courseServiceImp = courseServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @GetMapping()
    @ApiOperation(value = "Show all Courses")
    public ResponseEntity<BaseApiResponse<List<Course>>> selectAll()
    {
        String selected=message.selected("Courses");
        String dataEmpty=message.dataEmpty("Courses");
        String errorOperation=message.errorOperation("Courses");
        BaseApiResponse<List<Course>> response=new BaseApiResponse<>();
        try{
            List<Course> value=courseServiceImp.findAllCourse();
            if(!value.isEmpty())
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
