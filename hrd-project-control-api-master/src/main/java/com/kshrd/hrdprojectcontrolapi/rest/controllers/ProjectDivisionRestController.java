package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectDivision;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.request.projectdivision.ProjectDivisionApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import com.kshrd.hrdprojectcontrolapi.services.projectdivision.ProjectDivisionServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/project-division")
@Api(tags = "Project Divison", value = "Project Division", description = "Controller for project division control")
public class ProjectDivisionRestController {


    private ProjectDivisionServiceImp projectDivisionServiceImp;

    private MessageProperties message;


    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public ProjectDivisionRestController(ProjectDivisionServiceImp projectDivisionServiceImp) {
        this.projectDivisionServiceImp = projectDivisionServiceImp;
    }

    /**
     * insert project division with many users as array, schedule type id and project id
     * @param projectDivisionApiRequest
     * @return
     */
    @PostMapping()
    public ResponseEntity<BaseApiResponse<String>> insert(@RequestBody ProjectDivisionApiRequest projectDivisionApiRequest)
    {

        //for show message
        String inserted=message.inserted(Resources.USER.getName());
        String insertError=message.insertError(Resources.USER.getName());
        String errorOperation=message.errorOperation(Resources.USER.getName());

        ScheduleTypeFilterDocumentAndProject getValues;
        BaseApiResponse<String> response=new BaseApiResponse<>();
        try {
            List<Integer> data=projectDivisionApiRequest.getProjectDivisionUserApiRequests();
            int projectId=projectDivisionApiRequest.getProjectId();
            int scheduleTypeId=projectDivisionApiRequest.getScheduleTypeId();
            getValues =projectDivisionServiceImp.documentAndProjects(scheduleTypeId);
            int documentId=getValues.getDocumentScheduleId();
            int scheduleId=getValues.getProjectScheduleId();

            if(!data.isEmpty())
            {

                ProjectDivision checkUser=new ProjectDivision();
                CheckProject checkProject=new CheckProject();
                Boolean check=false;
                Boolean projectCheck=false;
                for (Integer values1:data) {
                    checkUser=projectDivisionServiceImp.findOne(projectId,values1);

                    if(checkUser!=null){
                        if(checkUser.getUserId()==values1)
                        {
                            check=true;
                        }
                        if(checkUser.getProjectId()==projectId)
                        {
                            projectCheck=true;
                        }
                    }
                    checkProject=projectDivisionServiceImp.findProjectId(projectId);
                    if(checkProject!=null)
                    {
                        projectCheck=true;
                    }

                }
                if(!check) {
                    for (Integer values : data) {
                        projectDivisionServiceImp.insertUserDivision(new ProjectDivision(projectId, values, scheduleTypeId, documentId, scheduleId));
                    }
                    if(!projectCheck)
                    {
                        // Call projectDivisionServiceImp's service to find all project schedule main task by id of schedule type
                        List<ProjectMainTaskApiResponse> listOfProjectScheduleMainTask = projectDivisionServiceImp.findAllProjectMainTask(scheduleTypeId);

                        for (ProjectMainTaskApiResponse rec : listOfProjectScheduleMainTask) {

                            // Convert startDate type as "String" into type as "Date"
                            Date startDate = convertTypeOfStringToDate(rec.getStartDate());

                            //  // Convert endDate type as "String" into type as "Date"
                            Date endDate = convertTypeOfStringToDate(rec.getEndDate());

                            // Call projectDivisionServiceImp's service to insert project schedule main task into table "task"
                            projectDivisionServiceImp.insertMainTask(rec.getTaskName(), startDate, endDate, projectId);

                        }

                /*
                    - Call projectDivisionServiceImp's service to insert "Document task" after insert main task into table "task"
                    - Purpose to make "Document task" to be parent_task_id of all document task that has get
                */
                        DocumentTaskDeadlineApiResponse documentTaskDeadline = projectDivisionServiceImp.findDocumentTaskDeadlineByScheduleTypeId(scheduleTypeId);

                        // Convert documentTaskStartDate type as "String" into type as "Date"
                        Date documentTaskStartDate = convertTypeOfStringToDate(documentTaskDeadline.getStartDate());

                        // Convert documentTaskEndDate type as "String" into type as "Date"
                        Date documentTaskEndDate = convertTypeOfStringToDate(documentTaskDeadline.getEndDate());

                        // Call projectDivisionServiceImp's service to insert document main task into table "task" by id of project
                        projectDivisionServiceImp.insertMainTask("Document", documentTaskStartDate, documentTaskEndDate, projectId);

                        // Call projectDivisionServiceImp's service to find id of document main task in table "task" by id of project
                        int idOfDocumentMainTask = projectDivisionServiceImp.findIdOfDocumentTaskByProjectId(projectId);

                        // Call projectDivisionServiceImp's service to insert all document task into table "task" and reference to "idOfDocumentMainTask" as their parent_task_id
                        List<DocumentTaskApiResponse> listOfAllDocumentTask = projectDivisionServiceImp.findAllDocumentTask(scheduleTypeId);

                        for (DocumentTaskApiResponse rec : listOfAllDocumentTask) {

                            // Convert docSubTaskStartDate type as "String" into type as "Date"
                            Date docSubTaskStartDate = convertTypeOfStringToDate(rec.getStartDate());

                            // Convert docSubTaskEndDate type as "String" into type as "Date"
                            Date docSubTaskEndDate = convertTypeOfStringToDate(rec.getEndDate());

                            // Call projectDivisionServiceImp's service to insert document sub task into table "task" by reference to "idOfDocumentMainTask" as its parent_task_id
                            projectDivisionServiceImp.insertDocumentSubTask(rec.getTaskName(), 0, docSubTaskStartDate, docSubTaskEndDate, idOfDocumentMainTask, projectId);
                        }

                        // Call projectDivisionServiceImp's service to find all id of parent_task_id in each project, purpose to make default "subtask" to it as default template
                        List<Integer> listOfParentTaskId = projectDivisionServiceImp.findAllIdOfParentTaskByProjectId(projectId);

                        for (int mainTaskId : listOfParentTaskId) {
                            // Call projectDivisionServiceImp's service to insert default sub task into table "task" by reference to each "parent_task_id"
                            projectDivisionServiceImp.insertDefaultSubTaskToEachParentByProjectId("Your subtask is here", 0, mainTaskId, projectId);
                        }
                    }

                    response.setMessage(inserted);
                    response.setStatus(HttpStatus.CREATED);
                }
                else
                {
                    response.setMessage("User name is already exit.");
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                response.setMessage(insertError);
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
     * Get all users in each project by project id
     * @param id
     * @return
     */
    @GetMapping()
    @ApiOperation("Show all users in each project")
    public ResponseEntity<BaseApiResponse<List<ProjectDivisionApiResponse>>> findAllUserByProjectId(@RequestParam int id)
    {
        //for show message
        String selected= message.selected("Users of Project Division");
        String dataEmpty=message.dataEmpty("Users of Project Division");
        String errorOperation=message.errorOperation("Users of Project Division");

        BaseApiResponse<List<ProjectDivisionApiResponse>> response=new BaseApiResponse<>();
        try {
            List<ProjectDivisionApiResponse> value=projectDivisionServiceImp.findAllUserByProjectId(id);
            if(!value.isEmpty()) //have data
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
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get project feature and objective by project id
     * @param id
     * @return
     */
    @GetMapping("/project")
    @ApiOperation("Show project feature and objective")
    public ResponseEntity<BaseApiResponse<List<Project>>> findProjectDivisionByProjectId(@RequestParam int id)
    {

        String selected= message.selected("Project");
        String dataEmpty=message.dataEmpty("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<List<Project>> response=new BaseApiResponse<>();
        try {
            List<Project> value=projectDivisionServiceImp.findProjectDivisionByProjectId(id);
            if(!value.isEmpty()) //have data
            {
                response.setMessage(selected);
                response.setData(value);
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

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @ApiOperation("Show project feature and objective")
    public ResponseEntity<BaseApiResponse<List<ProjectUserApiResponse>>> findProjectDivisionByUserId(@PathVariable int id)
    {

        String selected= message.selected("Project");
        String dataEmpty=message.dataEmpty("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<List<ProjectUserApiResponse>> response=new BaseApiResponse<>();
        try {
            List<ProjectUserApiResponse> value=projectDivisionServiceImp.findProjectDivisionByUserId(id);
            if(!(value==null)) //have data
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);

            }
            else
            {
                response.setMessage(dataEmpty);
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
     * This method is create for convert from data that type of "java.lang.String" to type "java.util.Date"
     * @param text
     * @return
     */
    public Date convertTypeOfStringToDate(String text) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(text);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     *
     * @param project_division_id
     * @return
     */
    @DeleteMapping("/{project_division_id}")
    @ApiOperation("Delete Project_division_id")
    public ResponseEntity<BaseApiResponse<List<ProjectDivision>>> deletePojectDivision(@PathVariable int project_division_id) {
        String deleted= message.deleted("Project");
        String dataEmpty=message.dataEmpty("Project");
        String errorOperation=message.errorOperation("Project");

        BaseApiResponse<List<ProjectDivision>> response=new BaseApiResponse<>();
        try {
            if (projectDivisionServiceImp.deleteProjectDivision(project_division_id)){
                response.setMessage(deleted);
                response.setStatus(HttpStatus.OK);
            }

            else {
                response.setMessage(dataEmpty);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

}
