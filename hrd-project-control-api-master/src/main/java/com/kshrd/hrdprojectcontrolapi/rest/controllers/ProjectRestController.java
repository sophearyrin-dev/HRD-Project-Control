package com.kshrd.hrdprojectcontrolapi.rest.controllers;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectApiFilterResponse;
import com.kshrd.hrdprojectcontrolapi.services.project.ProjectServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This rest controller is used for project CRUD
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/projects")
@Api(tags = "Project", value = "Project", description = "Controller for project control")
public class ProjectRestController {

    private ProjectServiceImp projectServiceImp;

    private MessageProperties message;

    @Autowired
    public ProjectRestController(ProjectServiceImp projectServiceImp) {
        this.projectServiceImp = projectServiceImp;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }


    /**
     * insert project
     * @param projectApiRequest
     * @return
     */
    @PostMapping()
    @ApiOperation("Insert Project")
    public ResponseEntity<BaseApiResponse<ProjectApiRequest>> insertProject(@RequestBody ProjectApiRequest projectApiRequest)
    {

        //for show message
        String inserted=message.inserted("Project");
        String insertError=message.insertError("Project");
        String errorOperation=message.errorOperation("Project");
        BaseApiResponse<ProjectApiRequest> response=new BaseApiResponse<>();
        try {

            Boolean value=projectServiceImp.insertProject(projectApiRequest);
            if(value)
            {
                response.setMessage(inserted);
                response.setData(projectApiRequest);
                response.setStatus(HttpStatus.CREATED);
            }
            else
            {
                response.setMessage(insertError);
                response.setData(projectApiRequest);
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
     * Update project by project id
     * @param projectUpdateApiRequest
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation("Update project by project id")
    public ResponseEntity<BaseApiResponse<ProjectUpdateApiRequest>> updateProject(@RequestBody ProjectUpdateApiRequest projectUpdateApiRequest, @PathVariable int id)
    {

        //for show message
        String updated=message.updated("Project");
        String updateError=message.updateError("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<ProjectUpdateApiRequest> response=new BaseApiResponse<>();
        try {
            projectUpdateApiRequest.setProjectId(id);
            Boolean value=projectServiceImp.updateProject(projectUpdateApiRequest);

            if(value)
            {
                response.setMessage(updated);
                response.setData(projectUpdateApiRequest);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(updateError);
                response.setData(projectUpdateApiRequest);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Delete project by changing status to false
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete project  by project id")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {
        //for show message
        String deleted=message.deleted("Project");
        String deleteError=message.deleteError("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try{

            Boolean value=projectServiceImp.deleteProject(id);

            if(value)
            {
                response.setMessage(deleted);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(deleteError);
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
     * -Filter (Generation and course) all project name by filter project_id,
     * use class (DailyReportAPiFilterResponse)
     *      *       -view data by(ProjectApiFilterResponse)
     *      *       - filter by class(ProjectFilter)
     * @param projectFilter
     * @return
     */

    @GetMapping("/filter")
    @ApiOperation("show all project name by filter generation and course")
    public ResponseEntity<BaseApiResponse<List<ProjectApiFilterResponse>>> FilterbyGenerationAndCourse(ProjectFilter projectFilter) {

        String selected=message.selected("Projects");
        String dataEmpty=message.dataEmpty("Project");
        String errorOperation=message.errorOperation("Project");


        BaseApiResponse<List<ProjectApiFilterResponse>> response = new BaseApiResponse<>();
        try {
            List<ProjectApiFilterResponse> value = projectServiceImp.findAllWithFilter(projectFilter);
            if(!value.isEmpty())
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else {
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
     * -Get project info by id,
     * use class (DailyReportAPiFilterResponse)
     *      *       -view data by(ProjectApiGetIdResponse )
     *      *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>>GetById(@PathVariable int id){

        String selected=message.selected("Projects");
        String dataEmpty=message.dataEmpty("Project");
        String dataNotFound = message.idNotFound("Project");
        String errorOperation=message.errorOperation("Project");

        Map<String, Object>res = new HashMap<>();
        try {
            Project b = projectServiceImp.findOne(id);
            if (b == null) {
                res.put("message", errorOperation);
                return ResponseEntity.notFound().build();
            }
            else
                {
                    res.put("message", selected);
                    res.put("data", b);
                    return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }
        catch (Exception e)
        {
            res.put("message",errorOperation);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        }
    }


    //TODO: Get all projects
    /**
     * Find all projects
     * @return
     */
    @GetMapping
    @ApiOperation("Show all projects")
    public ResponseEntity<BaseApiResponse<List<Project>>> selectAll() {

        //for show message
        String selected= message.selected("Projects");
        String dataEmpty=message.dataEmpty("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<List<Project>> response = new BaseApiResponse<>();
        try {
            List<Project> projects = projectServiceImp.findAllProject();
            if(!projects.isEmpty()) //have data
            {
                response.setMessage(selected);
                response.setData(projects);
                response.setStatus(HttpStatus.OK);

            }
            else
            {
                response.setMessage(dataEmpty);
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

}