package com.kshrd.hrdprojectcontrolapi.repositories.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import com.kshrd.hrdprojectcontrolapi.repositories.task.provider.SubTaskProvider;
import com.kshrd.hrdprojectcontrolapi.repositories.task.provider.TaskProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequestForTeamMember;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.CountIssueInProjectApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskIssueApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 *      This SubTaskRepository is used for map data between repository and database
 */
@Repository
public interface SubTaskRepository {

    // TODO: This method is used for get all subtasks by id of project
    @SelectProvider(type = SubTaskProvider.class, method = "findAllSubTaskByProjectId")
    @Results(id = "mappingResult", value = {
            @Result(property = "subTaskId", column = "id"),
            @Result(property = "subTaskName", column = "sub_task"),
            @Result(property = "handlerId", column = "handler_id"),
            @Result(property = "handler", column = "username"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "mainTaskName", column = "parent_task_name")
    })
    List<SubTaskApiResponse> findAllSubTaskByProjectId(@Param("filter") SubTaskProjectFilter subTaskProjectFilter);

    // TODO: This method is used for get all subtasks in each project by id of user
    @SelectProvider(type = SubTaskProvider.class, method = "findAllSubTaskInProjectByUserId")
    @ResultMap("mappingResult")
    List<SubTaskApiResponse> findAllSubTaskInProjectByUserId(@Param("filter") SubTaskUserFilter subTaskUserFilter);

    // TODO: This method is used for get all subtasks by id of project and parentTaskId
    @SelectProvider(type = SubTaskProvider.class, method = "findAllSubTaskByParentTaskIdInProject")
    @ResultMap("mappingResult")
    List<SubTaskApiResponse> findAllSubTaskByParentTaskIdInProject(@Param("filter") SubTaskFilterByParentTask subTaskFilterByParentTask);

    // TODO: This method is used for update subtasks
    @UpdateProvider(type = SubTaskProvider.class, method = "updateSubTask")
    Boolean updateSubTask(@Param("subTaskId") int subTaskId, @Param("subTaskUpdateApiRequest") SubTaskUpdateApiRequest subTaskUpdateApiRequest);

    // TODO: This method is used for update subtask of each project
    @UpdateProvider(type = SubTaskProvider.class, method = "updateSubTaskByMember")
    Boolean updateSubTaskByMember(@Param("subTaskId") int subTaskId, @Param("subTaskUpdateApiRequestForTeamMember") SubTaskUpdateApiRequestForTeamMember subTaskUpdateApiRequestForTeamMember);

     // TODO: This method is used for remove subtask (permission only for mentor or team leader) of each project
    @DeleteProvider(type = SubTaskProvider.class, method = "deleteSubTask")
    Boolean deleteSubTask(@Param("subTaskId") int subTaskId);

    @InsertProvider(type = SubTaskProvider.class, method = "insertAllSubTaskByParentTaskId")
    @Results({
            @Result(property = "subTaskName", column = "name"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "handlerId", column = "handler_id"),
            @Result(property = "parentTaskId", column = "parent_task_id"),
            @Result(property = "projectId", column = "project_id")
    })
    Boolean insertAllSubTaskByParentTaskId(@Param("subTaskName") String subTaskName, @Param("percentage") int percentage, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("handlerId") int handlerId, @Param("projectId") int projectId, @Param("parentTaskId") int parentTaskId, @Param("priority") char priority);

    // TODO: This method is used for get all issue from subtask in each project
    @SelectProvider(type = SubTaskProvider.class, method = "filterIssueInSubTask")
    @Results({
            @Result(property = "subTaskId", column = "id"),
            @Result(property = "handler", column = "username")
    })
    List<SubTaskIssueApiResponse> findAllIssueInEachProject(@Param("projectId") int projectId);

    // TODO: This method is used for count issue from subtask in each project
    @SelectProvider(type = SubTaskProvider.class, method = "countIssueInProject")
    CountIssueInProjectApiResponse countIssueInProject(@Param("projectId") int projectId);

    // TODO: This method is used for update data of column "issue" to NULL
    @UpdateProvider(type = SubTaskProvider.class, method = "updateSubTaskToResolveIssue")
    Boolean updateSubTaskToResolveIssue(@Param("subTaskId") int subTaskId);

    // TODO: This method is used for update data of column "issue" to NULL
    @SelectProvider(type = SubTaskProvider.class, method = "findAllIssueByParentTaskId")
    @Results({
            @Result(property = "subTaskId", column = "id"),
            @Result(property = "handler", column = "username")
    })
    List<SubTaskIssueApiResponse> findAllIssueByParentTaskId(@Param("parentTaskId") int parentTaskId);

}
