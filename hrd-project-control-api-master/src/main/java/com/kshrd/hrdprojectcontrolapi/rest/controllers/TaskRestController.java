package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressOfAllProjectFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskUpdateFilter;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import com.kshrd.hrdprojectcontrolapi.services.task.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *      This rest controller is used for task GET,POST,PUT
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/tasks")
@Api(tags = "Task", value = "Task", description = "Controller for task control")
public class TaskRestController {

    TaskService taskService;

    private MessageProperties message;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    /**
     * This method is used for update id of task handler of each project
     * @param taskUpdateFilter
     * @param taskUpdateApiRequest
     * @return
     */
    @PutMapping()
    @ApiOperation("Update handler of main task in project")
    public ResponseEntity<BaseApiResponse<TaskUpdateApiRequest>> updateMainTaskHandler(TaskUpdateFilter taskUpdateFilter, @RequestBody TaskUpdateApiRequest taskUpdateApiRequest) {

        String updated=message.updated(Resources.TASK.getName()+" handler");
        String updateError=message.updateError(Resources.TASK.getName());
        String errorOperation=message.errorOperation(Resources.TASK.getName());

        BaseApiResponse<TaskUpdateApiRequest> response = new BaseApiResponse<>();

        try {

            Boolean value = taskService.updateMainTaskHandler(taskUpdateFilter, taskUpdateApiRequest);

            if(value) {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
                response.setData(taskUpdateApiRequest);
            } else {
                response.setMessage(updateError);
                response.setData(taskUpdateApiRequest);
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
     * This method is used for find progress of each task in project
     * @param taskProgressFilter
     * @return
     */
    @GetMapping()
    @ApiOperation("Show all progress of all main task by id of project")
    public ResponseEntity<BaseApiResponse<List<ProgressOfAllMainTaskInProjectApiResponse>>> findProgressOfAllMainTaskByProjectId(TaskProgressFilter taskProgressFilter) {

        String selected = message.selected("Task Progress");
        String dataEmpty = message.dataEmpty("Task Progress");
        String errorOperation = message.errorOperation("Task Progress");

        BaseApiResponse<List<ProgressOfAllMainTaskInProjectApiResponse>> response = new BaseApiResponse<>();

        try {

            List<ProgressOfAllMainTaskInProjectApiResponse> value = taskService.findProgressOfAllMainTaskByProjectId(taskProgressFilter);

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
     * This method is used for ...
     * @param taskProgressOfAllProjectFilter
     * @return
     */
    @GetMapping("all-project-progress")
    @ApiOperation("Show all project progress")
    public ResponseEntity<BaseApiResponse<List<ProgressOfAllProjectApiResponse>>> findTaskProgressOfAllProject(TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter) {

        String selected = message.selected("All Project Progress");
        String dataEmpty = message.dataEmpty("All Project Progress");
        String errorOperation = message.errorOperation("All Project Progress");

        BaseApiResponse<List<ProgressOfAllProjectApiResponse>> response = new BaseApiResponse<>();

        try {

            List<TaskProgressOfAllProjectApiResponse> listOfTaskProgressOfAllProject = taskService.findTaskProgressOfAllProject(taskProgressOfAllProjectFilter);
            List<DocumentTaskPercentageApiResponse> listOfDocumentTaskOfAllProject = taskService.findAllDocumentTaskPercentage(taskProgressOfAllProjectFilter);

            List<ProgressOfAllProjectApiResponse> projectProgress = new ArrayList<>();

            if(!listOfTaskProgressOfAllProject.isEmpty() && !listOfDocumentTaskOfAllProject.isEmpty()) {

                List<Integer> documentPercentageList = new ArrayList<>();
                for (TaskProgressOfAllProjectApiResponse data : listOfTaskProgressOfAllProject) {
                    documentPercentageList.add(100 - data.getTotalBaseTaskPercentage());
                }

                // Find all issue in each project
                List<CountIssueInProjectApiResponse> listOfNumberOfIssueInProject = new ArrayList<>();

                List<Float> listOfTaskDocumentProgress = new ArrayList<>();
                for (int i = 0; i < documentPercentageList.size(); i++) {
                    listOfTaskDocumentProgress.add((documentPercentageList.get(i) * listOfDocumentTaskOfAllProject.get(i).getDocumentTaskPercentage()) / 100);

                    // Find all numberOfIssue in each project
                    listOfNumberOfIssueInProject.add(taskService.countIssueToShowInProject(listOfTaskProgressOfAllProject.get(i).getProjectId()));
                }

                for (int j = 0; j < listOfTaskProgressOfAllProject.size(); j++) {
                    float percentageOfProject = Math.round(listOfTaskDocumentProgress.get(j) + listOfTaskProgressOfAllProject.get(j).getProjectProgressPercentage());
                    projectProgress.add(new ProgressOfAllProjectApiResponse(listOfTaskProgressOfAllProject.get(j).getProjectId(), listOfTaskProgressOfAllProject.get(j).getProjectName(), percentageOfProject, listOfTaskProgressOfAllProject.get(j).getGenerationName(), listOfTaskProgressOfAllProject.get(j).getCourseName(), listOfNumberOfIssueInProject.get(j).getNumberOfIssue()));
                }

                response.setMessage(selected);
                response.setData(projectProgress);
                response.setStatus(HttpStatus.OK);

                // set data of temporary list to null for clear some memory
                documentPercentageList.removeAll(documentPercentageList);
                listOfTaskDocumentProgress.removeAll(listOfTaskDocumentProgress);
                listOfNumberOfIssueInProject.removeAll(listOfNumberOfIssueInProject);
            }
            else {
                response.setMessage(dataEmpty);
                response.setData(projectProgress);
                response.setStatus(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{projectId}")
    @ApiOperation("Show project progress by id of project")
    public ResponseEntity<BaseApiResponse<List<ProgressOfAllProjectApiResponse>>> findAllTaskProgressByProjectId(@PathVariable int projectId) {

        String selected = message.selected("Project Progress");
        String dataEmpty = message.dataEmpty("Project Progress");
        String errorOperation = message.errorOperation("Project Progress");

        BaseApiResponse<List<ProgressOfAllProjectApiResponse>> response = new BaseApiResponse<>();

        try {

            List<TaskProgressOfAllProjectApiResponse> taskProgressOfProject = taskService.findAllTaskProgressByProjectId(projectId);

            List<DocumentTaskPercentageApiResponse> documentTaskOfProject = taskService.findDocumentTaskPercentageByProjectId(projectId);

            List<ProgressOfAllProjectApiResponse> projectProgress = new ArrayList<>();

            if(!taskProgressOfProject.isEmpty() && !documentTaskOfProject.isEmpty()) {

                List<Integer> documentPercentageList = new ArrayList<>();
                for (TaskProgressOfAllProjectApiResponse data : taskProgressOfProject) {
                    documentPercentageList.add(100 - data.getTotalBaseTaskPercentage());
                }

                // Find all issue in each project
                List<CountIssueInProjectApiResponse> listOfNumberOfIssueInProject = new ArrayList<>();

                List<Float> listOfTaskDocumentProgress = new ArrayList<>();
                for (int i = 0; i < documentPercentageList.size(); i++) {
                    listOfTaskDocumentProgress.add((documentPercentageList.get(i) * documentTaskOfProject.get(i).getDocumentTaskPercentage()) / 100);

                    // Find all numberOfIssue in each project
                    listOfNumberOfIssueInProject.add(taskService.countIssueToShowInProject(taskProgressOfProject.get(i).getProjectId()));
                }

                for (int j = 0; j < taskProgressOfProject.size(); j++) {
                    float percentageOfProject = Math.round(listOfTaskDocumentProgress.get(j) + taskProgressOfProject.get(j).getProjectProgressPercentage());
                    projectProgress.add(new ProgressOfAllProjectApiResponse(taskProgressOfProject.get(j).getProjectId(), taskProgressOfProject.get(j).getProjectName(), percentageOfProject, taskProgressOfProject.get(j).getGenerationName(), taskProgressOfProject.get(j).getCourseName(), listOfNumberOfIssueInProject.get(j).getNumberOfIssue()));
                }

                response.setMessage(selected);
                response.setData(projectProgress);
                response.setStatus(HttpStatus.OK);

                // set data of temporary list to null for clear some memory
                documentPercentageList.removeAll(documentPercentageList);
                listOfTaskDocumentProgress.removeAll(listOfTaskDocumentProgress);
                listOfNumberOfIssueInProject.removeAll(listOfNumberOfIssueInProject);
            }
            else {
                response.setMessage(dataEmpty);
                response.setData(projectProgress);
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
