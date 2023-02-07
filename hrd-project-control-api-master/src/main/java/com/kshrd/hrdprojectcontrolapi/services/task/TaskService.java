package com.kshrd.hrdprojectcontrolapi.services.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressOfAllProjectFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskUpdateFilter;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;

import java.util.List;

public interface TaskService {

    Boolean updateMainTaskHandler(TaskUpdateFilter taskUpdateFilter, TaskUpdateApiRequest taskUpdateApiRequest);
    List<ProgressOfAllMainTaskInProjectApiResponse> findProgressOfAllMainTaskByProjectId(TaskProgressFilter taskProgressFilter);
    List<DocumentTaskPercentageApiResponse> findAllDocumentTaskPercentage(TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter);
    List<TaskProgressOfAllProjectApiResponse> findTaskProgressOfAllProject(TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter);
    CountIssueInProjectApiResponse countIssueToShowInProject(int projectId);

    List<TaskProgressOfAllProjectApiResponse> findAllTaskProgressByProjectId(int projectId);
    List<DocumentTaskPercentageApiResponse> findDocumentTaskPercentageByProjectId(int projectId);

}
