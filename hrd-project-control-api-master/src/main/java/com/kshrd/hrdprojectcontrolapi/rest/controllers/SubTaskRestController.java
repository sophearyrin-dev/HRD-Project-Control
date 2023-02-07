package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.request.*;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import com.kshrd.hrdprojectcontrolapi.services.task.SubTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 *      This rest controller is used for subTask CRUD
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}")
@Api(tags = "Sub task", value = "Sub task", description = "Controller for sub task control")
public class SubTaskRestController {

    SubTaskService subTaskService;

    private MessageProperties message;

    @Autowired
    public void setSubTaskService(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    /**
     * This method is used for get all subtask filter by id of each project, generation and course
     * @param subTaskProjectFilter
     * @return
     */
    @GetMapping("/find-subtasks")
    @ApiOperation("Show all subtask from project")
    public ResponseEntity<BaseApiResponse<List<SubTaskApiResponse>>> findAllSubTaskByProjectId(SubTaskProjectFilter subTaskProjectFilter) {

        String selected = message.selected("Subtask");
        String dataEmpty = message.dataEmpty(Resources.SUBTASK.getName());
        String errorOperation = message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<List<SubTaskApiResponse>> response = new BaseApiResponse<>();

        try {
            List<SubTaskApiResponse> value = subTaskService.findAllSubTaskByProjectId(subTaskProjectFilter);
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
     * This method is used for get all sub task from each parent task by id of project, generation, course and parentTask
     * @param subTaskFilterByParentTask
     * @return
     */
    @GetMapping("/subtask")
    @ApiOperation("Show all subtasks from id of parent task in projects")
    public ResponseEntity<BaseApiResponse<List<SubTaskApiResponse>>> findAllSubTaskByParentTaskIdInProject(SubTaskFilterByParentTask subTaskFilterByParentTask) {

        String selected = message.selected("Subtask");
        String dataEmpty = message.dataEmpty(Resources.SUBTASK.getName());
        String errorOperation = message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<List<SubTaskApiResponse>> response = new BaseApiResponse<>();

        try {
            List<SubTaskApiResponse> value = subTaskService.findAllSubTaskByParentTaskIdInProject(subTaskFilterByParentTask);
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
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for get all subtask filter by id of each project, generation, course and user
     * @param subTaskUserFilter
     * @return
     */
    @GetMapping("/subtasks")
    @ApiOperation("Show all subtasks from project by id of user")
    public ResponseEntity<BaseApiResponse<List<SubTaskApiResponse>>> findAllSubTaskInProjectByUserId(SubTaskUserFilter subTaskUserFilter) {

        String selected = message.selected("Subtasks");
        String dataEmpty = message.dataEmpty(Resources.SUBTASK.getName());
        String errorOperation = message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<List<SubTaskApiResponse>> response = new BaseApiResponse<>();

        try {
            List<SubTaskApiResponse> value = subTaskService.findAllSubTaskInProjectByUserId(subTaskUserFilter);
            if(!value.isEmpty())
            {
                if(value.size()==1){
                    selected = message.selectedOne("Subtask");
                }
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
     * This method is used for update subtask
     * (only for member) of each project
     * @param subTaskId
     * @param subTaskUpdateApiRequest
     * @return
     */
    @PutMapping("/subtasks/{subTaskId}")
    @ApiOperation("Update subtask in project by team member")
    public ResponseEntity<BaseApiResponse<SubTaskUpdateApiRequestForTeamMember>> updateSubTaskByMember(@PathVariable int subTaskId, @RequestBody SubTaskUpdateApiRequestForTeamMember subTaskUpdateApiRequest) {

        String updated=message.updated(Resources.SUBTASK.getName());
        String updateError=message.updateError(Resources.SUBTASK.getName());
        String errorOperation=message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<SubTaskUpdateApiRequestForTeamMember> response =new BaseApiResponse<>();

        try {

            Boolean value = subTaskService.updateSubTaskByMember(subTaskId, subTaskUpdateApiRequest);

            if(value) {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
                response.setData(subTaskUpdateApiRequest);
            } else {
                response.setMessage(updateError);
                response.setData(subTaskUpdateApiRequest);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for update task of each project
     * (permission only team leader)
     * @param subTaskId
     * @param subTaskUpdateApiRequest
     * @return
     */
    @PutMapping("/subTask/{subTaskId}")
    @ApiOperation("Update subtask to project")
    public ResponseEntity<BaseApiResponse<SubTaskUpdateApiRequest>> updateSubTask(@PathVariable int subTaskId, @RequestBody SubTaskUpdateApiRequest subTaskUpdateApiRequest) {

        String updated=message.updated(Resources.SUBTASK.getName());
        String updateError=message.updateError(Resources.SUBTASK.getName());
        String errorOperation=message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<SubTaskUpdateApiRequest> response =new BaseApiResponse<>();

        try {
            Boolean value = subTaskService.updateSubTask(subTaskId, subTaskUpdateApiRequest);
            if(value) {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
                response.setData(subTaskUpdateApiRequest);
            } else {
                response.setMessage(updateError);
                response.setData(subTaskUpdateApiRequest);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for remove subtask from project
     * @param subTaskId
     * @return
     */
    @DeleteMapping("/subtasks/{subTaskId}")
    @ApiOperation("Delete subtask from project")
    public ResponseEntity<BaseApiResponse<String>> deleteSubTask(@PathVariable int subTaskId)
    {

        String deleted=message.deleted(Resources.SUBTASK.getName());
        String deleteError=message.deleteError(Resources.SUBTASK.getName());
        String errorOperation=message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<String> response = new BaseApiResponse<>();

        try {

            Boolean value=subTaskService.deleteSubTask(subTaskId);

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
     *
     * @param subTaskAllInsertApiRequest
     * @return
     */
    @PostMapping("/subtasks-all-insert")
    @ApiOperation("Insert all subtask to projects by id of parent task")
    public ResponseEntity<BaseApiResponse<SubTaskAllInsertApiRequest>> testInsertAllSubTaskByParentTaskId(@RequestBody SubTaskAllInsertApiRequest subTaskAllInsertApiRequest) {

        String inserted = message.inserted(Resources.SUBTASK.getName());
        String insertError = message.insertError(Resources.SUBTASK.getName());
        String errorOperation = message.errorOperation(Resources.SUBTASK.getName());

        int projectId = subTaskAllInsertApiRequest.getProjectId();
        int parentTaskId = subTaskAllInsertApiRequest.getParentTaskId();

        List<SubTaskApiRequest> listOfAllSubTaskForInsert = subTaskAllInsertApiRequest.getSubTaskApiRequests();

        BaseApiResponse<SubTaskAllInsertApiRequest> response = new BaseApiResponse<>();

        try {

            Boolean isInsertAll = false;
            for (SubTaskApiRequest rec : listOfAllSubTaskForInsert) {
                isInsertAll =  subTaskService.insertAllSubTaskByParentTaskId(rec.getSubTaskName(), rec.getPercentage(), rec.getStartDate(), rec.getEndDate(), rec.getHandlerId(), projectId, parentTaskId, rec.getPriority());
            }

            if(isInsertAll) {
                response.setMessage(inserted);
                response.setData(subTaskAllInsertApiRequest);
                response.setStatus(HttpStatus.CREATED);
            } else {
                response.setMessage(insertError);
                response.setData(subTaskAllInsertApiRequest);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for get all issue from subtask in each project
     * @param projectId
     * @return
     */
    @GetMapping("/subtasks-find-issue/{projectId}")
    @ApiOperation("Show all issue from project")
    public ResponseEntity<BaseApiResponse<List<SubTaskIssueApiResponse>>> findAllIssueInEachProject(@PathVariable int projectId) {

        String selected = message.selected("All Issue");
        String dataEmpty = message.dataEmpty("All Issue");
        String errorOperation = message.errorOperation("All Issue");

        BaseApiResponse<List<SubTaskIssueApiResponse>> response = new BaseApiResponse<>();
        try {
            List<SubTaskIssueApiResponse> value = subTaskService.findAllIssueInEachProject(projectId);
            if(!value.isEmpty()) {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     *
     * @param projectId
     * @return
     */
    @GetMapping("/subtasks-countissue/{projectId}")
    @ApiOperation("Count all issue from project")
    public ResponseEntity<BaseApiResponse<CountIssueInProjectApiResponse>> countIssueInProject(@PathVariable int projectId) {

        String selected = message.selected("Count Issue");
        String dataEmpty = message.dataEmpty("Count Issue");
        String errorOperation = message.errorOperation("Count Issue");

        BaseApiResponse<CountIssueInProjectApiResponse> response = new BaseApiResponse<>();

        try {
            CountIssueInProjectApiResponse value = subTaskService.countIssueInProject(projectId);
            response.setMessage(selected);
            response.setData(value);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for update data of issue in subtask to NULL
     * @param subTaskId
     * @return
     */
    @PutMapping("/subTask-resolve-issue/{subTaskId}")
    @ApiOperation("Update subtask to resolve issue in project")
    public ResponseEntity<BaseApiResponse<SubTaskUpdateApiRequest>> updateSubTaskToResolveIssue(@PathVariable int subTaskId) {

        String updated=message.updated(Resources.SUBTASK.getName());
        String updateError=message.updateError(Resources.SUBTASK.getName());
        String errorOperation=message.errorOperation(Resources.SUBTASK.getName());

        BaseApiResponse<SubTaskUpdateApiRequest> response = new BaseApiResponse<>();

        try {
            Boolean value = subTaskService.updateSubTaskToResolveIssue(subTaskId);
            if(value) {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage(updateError);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for get all issue by id of parent task from project
     * @param parentTaskId
     * @return
     */
    @GetMapping("/find-issue-by-parent-task/{parentTaskId}")
    @ApiOperation("Count all issue from project")
    public ResponseEntity<BaseApiResponse<List<SubTaskIssueApiResponse>>> findAllIssueByParentTaskId(@PathVariable int parentTaskId) {

        String selected = message.selected("All Issue");
        String dataEmpty = message.dataEmpty("All Issue");
        String errorOperation = message.errorOperation("All Issue");

        BaseApiResponse<List<SubTaskIssueApiResponse>> response = new BaseApiResponse<>();
        try {
            List<SubTaskIssueApiResponse> value = subTaskService.findAllIssueByParentTaskId(parentTaskId);
            if(!value.isEmpty()) {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}

