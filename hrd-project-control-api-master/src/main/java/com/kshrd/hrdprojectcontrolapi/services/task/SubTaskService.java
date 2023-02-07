package com.kshrd.hrdprojectcontrolapi.services.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequestForTeamMember;
import com.kshrd.hrdprojectcontrolapi.rest.response.CountIssueInProjectApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskIssueApiResponse;

import java.util.Date;
import java.util.List;

public interface SubTaskService {

    List<SubTaskApiResponse> findAllSubTaskByProjectId(SubTaskProjectFilter subTaskProjectFilter);
    List<SubTaskApiResponse> findAllSubTaskByParentTaskIdInProject(SubTaskFilterByParentTask subTaskFilterByParentTask);
    List<SubTaskApiResponse> findAllSubTaskInProjectByUserId(SubTaskUserFilter subTaskUserFilter);
    List<SubTaskIssueApiResponse> findAllIssueInEachProject(int projectId);
    CountIssueInProjectApiResponse countIssueInProject(int projectId);
    List<SubTaskIssueApiResponse> findAllIssueByParentTaskId(int parentTaskId);

    Boolean insertAllSubTaskByParentTaskId(String subTaskName, int percentage, Date startDate, Date endDate, int handlerId, int projectId, int parentTaskId, char priority);
    Boolean updateSubTask(int subTaskId, SubTaskUpdateApiRequest subTaskUpdateApiRequest);
    Boolean updateSubTaskByMember(int subTaskId, SubTaskUpdateApiRequestForTeamMember subTaskUpdateApiRequestForTeamMember);
    Boolean deleteSubTask(int subTaskId);
    Boolean updateSubTaskToResolveIssue(int subTaskId);

}
