package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleApiRequst;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectScheduleApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.projectschedule.ProjectScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for project schedule crud
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/project-schedule")
@Api(tags = "Project Schedule", value = "Project schedule", description = "Controller for project schedule Control")

public class ProjectScheduleRestController {

    ProjectScheduleService projectScheduleService;

    private Validation validation;

    private MessageProperties message;

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public ProjectScheduleRestController(ProjectScheduleService projectScheduleService) {
        this.projectScheduleService = projectScheduleService;
    }

    /**
     * This method use for delete project schedule by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete project schedule")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {

        //for show message
        String deleted=message.deleted("Project schedule");
        String deleteError=message.deleteError("Project schedule");
        String errorOperation=message.errorOperation("Project schedule");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try{

            Boolean value=projectScheduleService.deleteProjectSchedule(id);

            if(value)
            {
                response.setMessage(deleted);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(deleteError);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method use for delete project topic by id
     * @param scheduleAction
     * @return
     */

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update status project topic ")
    public ResponseEntity<BaseApiResponse<String>> update(@RequestBody ScheduleAction scheduleAction, @PathVariable int id)
    {

        //for show message
        String updated=message.updated("Project schedule");
        String updateError=message.updateError("Project schedule");
        String errorOperation=message.errorOperation("Project schedule");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try {

            scheduleAction.setId(id);
            Boolean value=projectScheduleService.updateStatusProjectSchedule(scheduleAction, scheduleAction.getId());

            if(value)
            {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(updateError);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
//            response.setMessage(errorOperation);
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * This method use for update project schedule by id
     * @param projectScheduleApiRequst
     * @return
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Update project schedule")
    public ResponseEntity<BaseApiResponse<ProjectScheduleApiRequst>> update(@RequestBody ProjectScheduleApiRequst projectScheduleApiRequst,@PathVariable int id)
    {

        //for show message
        String updated=message.updated("Project schedule");
        String updateError=message.updateError("Project schedule");
        String errorOperation=message.errorOperation("Project schedule");

        BaseApiResponse<ProjectScheduleApiRequst> response=new BaseApiResponse<>();

        try{

            boolean check=validation.checkInputString(projectScheduleApiRequst.getGroupMeeting());
            response.setData(projectScheduleApiRequst);

            if(check)
            {
                response.setMessage("Project topic is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            projectScheduleApiRequst.setId(id);
            Boolean value=projectScheduleService.updateProjectSchedule(projectScheduleApiRequst);

            if(value)
            {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(updateError);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method use for get all project schedule
     * @return
     */

    @GetMapping()
    @ApiOperation(value = "Show all project schedules")
    public ResponseEntity<BaseApiResponse<List<ProjectScheduleApiResponse>>> findAll()
    {

        //for show message
        String selected=message.selected("Project schedule");
        String dataEmpty=message.dataEmpty("Project schedule");
        String errorOperation=message.errorOperation("Project schedule");

        BaseApiResponse<List<ProjectScheduleApiResponse>> response=new BaseApiResponse<>();

        try {

            List<ProjectScheduleApiResponse> value=projectScheduleService.findAllProjectSchedule();

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
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * find one project schedule
     * @return
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Show one project schedules")
    public ResponseEntity<BaseApiResponse<ProjectScheduleApiResponse>> findOne(@PathVariable int id)
    {

        //for show message
        String selected=message.selected("Project schedule");
        String dataEmpty=message.dataEmpty("Project schedule");
        String errorOperation=message.errorOperation("Project schedule");

        BaseApiResponse<ProjectScheduleApiResponse> response=new BaseApiResponse<>();

        try {

            ProjectScheduleApiResponse value=projectScheduleService.findOneProjectSchedule(id);

            if(value!=null)
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
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
