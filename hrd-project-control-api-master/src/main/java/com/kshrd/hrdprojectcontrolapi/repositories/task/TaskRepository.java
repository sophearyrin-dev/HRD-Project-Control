package com.kshrd.hrdprojectcontrolapi.repositories.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.SubTaskCountIssueFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressOfAllProjectFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskUpdateFilter;
import com.kshrd.hrdprojectcontrolapi.repositories.task.provider.TaskProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *      This TaskRepository is used for map data between repository and database
 */
@Repository
public interface TaskRepository {

     // TODO: This method is used for find all progress of main task by id of project
    @SelectProvider(type = TaskProvider.class, method = "findProgressOfAllMainTaskByProjectId")
    @Results(id = "mappingResult", value = {
            @Result(property = "parentTaskId", column = "parent_task_id"),
            @Result(property = "parentTaskName", column = "parent_task_name"),
            @Result(property = "percentage", column = "main_task_percentage"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "numberOfIssue", column = "number_of_issue")
    })
    List<ProgressOfAllMainTaskInProjectApiResponse> findProgressOfAllMainTaskByProjectId(@Param("filter") TaskProgressFilter taskProgressFilter);

    // TODO: This method is used for update task handler of each project's task
    @UpdateProvider(type = TaskProvider.class, method = "updateMainTaskHandler")
    Boolean updateMainTaskHandler(@Param("filter") TaskUpdateFilter taskUpdateFilter, @Param("taskUpdateApiRequest") TaskUpdateApiRequest taskUpdateApiRequests);

    // TODO: This method is used for find percentage of all document task in each project
    @SelectProvider(type = TaskProvider.class, method = "findAllDocumentTaskPercentage")
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "documentTaskPercentage", column = "document_task_percentage")
    })
    List<DocumentTaskPercentageApiResponse> findAllDocumentTaskPercentage(@Param("filter") TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter);

    // TODO: This method is used for find all project progress
    @SelectProvider(type = TaskProvider.class, method = "findTaskProgressOfAllProject")
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "projectName", column = "project_name"),
            @Result(property = "totalBaseTaskPercentage", column = "total_base_task_percentage"),
            @Result(property = "projectProgressPercentage", column = "project_progress_percentage"),
            @Result(property = "generationName", column = "generation_name"),
            @Result(property = "courseName", column = "course_name")
    })
    List<TaskProgressOfAllProjectApiResponse> findTaskProgressOfAllProject(@Param("filter") TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter);

    // TODO: This method is used for count issue from subtask in each project
    @SelectProvider(type = TaskProvider.class, method = "countIssueToShowInProject")
    CountIssueInProjectApiResponse countIssueToShowInProject(@Param("projectId") int projectId);

    @SelectProvider(type = TaskProvider.class, method = "findAllTaskProgressByProjectId")
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "projectName", column = "project_name"),
            @Result(property = "totalBaseTaskPercentage", column = "total_base_task_percentage"),
            @Result(property = "projectProgressPercentage", column = "project_progress_percentage"),
            @Result(property = "generationName", column = "generation_name"),
            @Result(property = "courseName", column = "course_name")
    })
    List<TaskProgressOfAllProjectApiResponse> findAllTaskProgressByProjectId(@Param("projectId") int projectId);

    @SelectProvider(type = TaskProvider.class, method = "findDocumentTaskPercentageByProjectId")
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "documentTaskPercentage", column = "document_task_percentage")
    })
    List<DocumentTaskPercentageApiResponse> findDocumentTaskPercentageByProjectId(int projectId);

}
