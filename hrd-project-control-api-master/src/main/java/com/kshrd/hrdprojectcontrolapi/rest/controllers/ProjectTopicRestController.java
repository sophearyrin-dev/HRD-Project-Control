package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectTopicApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.projecttopic.ProjectTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for project topic crud(insert,update,delete,get)
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/project-schedule-topic")
@Api(tags = "Project topic", value = "Project topic", description = "Controller for Project topic Control")

public class ProjectTopicRestController {

    ProjectTopicService projectTopicService;

    private Validation validation;

    private MessageProperties message;

    private List<String> emptyData;

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public ProjectTopicRestController(ProjectTopicService projectTopicService) {
        this.projectTopicService = projectTopicService;
    }

    /**
     * This method use for insert project topic
     * @param projectTopicApiRequest
     * @return
     */

//    @PostMapping()
//    @ApiOperation(value = "Insert project topic")
//    public ResponseEntity<BaseApiResponse<ProjectTopicApiRequest>> insert(@RequestBody ProjectTopicApiRequest projectTopicApiRequest)
//    {
//
//        //for show message
//        String inserted=message.inserted("Project topic");
//        String insertError=message.insertError("Project topic");
//        String errorOperation=message.errorOperation("Project topic");
//
//        BaseApiResponse<ProjectTopicApiRequest> response=new BaseApiResponse<>();
//
//        try {
//
//            boolean check=validation.checkInputString(projectTopicApiRequest.getName());
//            response.setData(projectTopicApiRequest);
//
//            if(check)
//            {
//                response.setMessage("Project topic is invalid.");
//                response.setStatus(HttpStatus.BAD_REQUEST);
//                return ResponseEntity.ok(response);
//            }
//
//            Boolean value=projectTopicService.insertProjectTopic(projectTopicApiRequest);
//
//            if(value)
//            {
//                response.setMessage(inserted);
//                response.setStatus(HttpStatus.CREATED);
//            }
//            else
//            {
//                response.setMessage(insertError);
//                response.setStatus(HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e)
//        {
//            response.setMessage(errorOperation);
//            response.setStatus(HttpStatus.BAD_REQUEST);
//        }
//
//        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
//        return ResponseEntity.ok(response);
//    }

    /**
     * This method use for delete project topic by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete project topic by id")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {

        //for show message
        String deleted=message.deleted("Project topic");
        String deleteError=message.deleteError("Project topic");
        String errorOperation=message.errorOperation("Project topic");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try {

            Boolean value=projectTopicService.deleteProjectTopic(id);

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
     * This method use for update project topic by id
     * @param projectTopicApiRequest
     * @return
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Update project topic by id")
    public ResponseEntity<BaseApiResponse<ProjectTopicApiRequest>> update(@RequestBody ProjectTopicApiRequest projectTopicApiRequest,@PathVariable int id)
    {

        //for show message
        String updated=message.updated("Project topic");
        String updateError=message.updateError("Project topic");
        String errorOperation=message.errorOperation("Project topic");

        BaseApiResponse<ProjectTopicApiRequest> response=new BaseApiResponse<>();

        try {

            boolean check=validation.checkInputString(projectTopicApiRequest.getName());
            response.setData(projectTopicApiRequest);

            if(check)
            {
                response.setMessage("Project topic is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }
            projectTopicApiRequest.setId(id);
            Boolean value=projectTopicService.updateProjectTopic(projectTopicApiRequest);

            if(value)
            {
                response.setMessage(updated);
                response.setStatus(HttpStatus.CREATED);
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
     * This method use for get project topic by id
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Show all project topics by project schedule id")
    public ResponseEntity<BaseApiResponse<List<ProjectTopicApiResponse>>> findAll(@PathVariable int id)
    {

        //for show message
        String selected=message.selected("Project topics");
        String dataEmpty=message.dataEmpty("Project topic");
        String errorOperation=message.errorOperation("Project topic");

        BaseApiResponse<List<ProjectTopicApiResponse>> response=new BaseApiResponse<>();

        try {

            List<ProjectTopicApiResponse> value=projectTopicService.findAllProjectTopic(id);

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
}
