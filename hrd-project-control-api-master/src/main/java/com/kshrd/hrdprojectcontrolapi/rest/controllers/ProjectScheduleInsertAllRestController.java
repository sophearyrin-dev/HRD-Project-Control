package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleApiRequst;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleAllInsertApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicInsertAllApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.projectschedule.ProjectScheduleService;
import com.kshrd.hrdprojectcontrolapi.services.projecttopic.ProjectTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * This class use for insert project schedule and project topic.
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/project-schedule-insert-all")
@Api(tags = "Project schedule and project topic", value = "Project schedule and project topic", description = "Controller for project schedule and project topic Control")

public class ProjectScheduleInsertAllRestController {

    private Validation validation;

    private MessageProperties message;

    ProjectScheduleService projectScheduleService;

    ProjectTopicService projectTopicService;

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public ProjectScheduleInsertAllRestController(ProjectScheduleService projectScheduleService, ProjectTopicService projectTopicService) {
        this.projectScheduleService = projectScheduleService;
        this.projectTopicService = projectTopicService;
    }

    /**
     * This method use for insert project schedule and multi project topic.
     * @param scheduleAllInsertApiRequest
     * @return
     */

    @PostMapping()
    @ApiOperation(value = "Insert project schedule and project topic")
    public ResponseEntity<BaseApiResponse<ProjectScheduleAllInsertApiRequest>> insertAll(@RequestBody ProjectScheduleAllInsertApiRequest scheduleAllInsertApiRequest)
    {

        //for show message
        String inserted=message.inserted("Project schedule and Multi project topics");
        String insertError=message.insertError("Project schedule and Multi project topics");
        String errorOperation=message.errorOperation("Project schedule and Multi project topics");

        BaseApiResponse<ProjectScheduleAllInsertApiRequest> response=new BaseApiResponse<>();

        try{

            ProjectSchedule projectSchedule=new ProjectSchedule();

            List<ProjectTopicApiRequest> projectTopicApiRequest=new ArrayList<>();

            ProjectScheduleApiRequst projectScheduleApiRequst=scheduleAllInsertApiRequest.getProjectScheduleApiRequst();

            BeanUtils.copyProperties(projectScheduleApiRequst,projectSchedule);

            response.setData(scheduleAllInsertApiRequest);

            boolean check=validation.checkInputString(projectSchedule.getGroupMeeting());

            if(check)
            {
                response.setMessage("Project schedule invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            Boolean value=projectScheduleService.insertProjectSchedule(projectSchedule);

            int id=projectSchedule.getId();

            List<ProjectTopicInsertAllApiRequest> results=scheduleAllInsertApiRequest.getProjectTopicApiRequestList();

            for (ProjectTopicInsertAllApiRequest source:results) {

                ProjectTopicApiRequest target= new ProjectTopicApiRequest();
                BeanUtils.copyProperties(source , target);
                projectTopicApiRequest.add(target);

            }
            for (ProjectTopicApiRequest values:projectTopicApiRequest) {

                values.setProjectScheduleId(id);

                boolean checks=validation.checkInputString(values.getName());

                if(checks)
                {
                    response.setMessage("Project schedule invalid.");
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    return ResponseEntity.ok(response);
                }

                projectTopicService.insertProjectTopic(values);

            }
            if(value)
            {

                response.setMessage(inserted);
                response.setStatus(HttpStatus.CREATED);

            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
