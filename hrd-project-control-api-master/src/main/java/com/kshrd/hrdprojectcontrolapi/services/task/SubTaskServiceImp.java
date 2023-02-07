package com.kshrd.hrdprojectcontrolapi.services.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import com.kshrd.hrdprojectcontrolapi.repositories.task.SubTaskRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequestForTeamMember;
import com.kshrd.hrdprojectcontrolapi.rest.request.SubTaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.CountIssueInProjectApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.SubTaskIssueApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubTaskServiceImp implements SubTaskService {

    SubTaskRepository subTaskRepository;

    @Autowired
    public void setSubTaskRepository(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    public List<SubTaskApiResponse> findAllSubTaskByProjectId(SubTaskProjectFilter subTaskProjectFilter) {
        return subTaskRepository.findAllSubTaskByProjectId(subTaskProjectFilter);
    }

    @Override
    public List<SubTaskApiResponse> findAllSubTaskByParentTaskIdInProject(SubTaskFilterByParentTask subTaskFilterByParentTask) {
        return subTaskRepository.findAllSubTaskByParentTaskIdInProject(subTaskFilterByParentTask);
    }

    @Override
    public List<SubTaskApiResponse> findAllSubTaskInProjectByUserId(SubTaskUserFilter subTaskUserFilter) {
        return subTaskRepository.findAllSubTaskInProjectByUserId(subTaskUserFilter);
    }

    @Override
    public Boolean updateSubTaskByMember(int subTaskId, SubTaskUpdateApiRequestForTeamMember subTaskUpdateApiRequestForTeamMember) {
        return subTaskRepository.updateSubTaskByMember(subTaskId, subTaskUpdateApiRequestForTeamMember);
    }

    @Override
    public Boolean updateSubTask(int subTaskId, SubTaskUpdateApiRequest subTaskUpdateApiRequest) {
        return subTaskRepository.updateSubTask(subTaskId, subTaskUpdateApiRequest);
    }

    @Override
    public Boolean deleteSubTask(int subTaskId) {
        return subTaskRepository.deleteSubTask(subTaskId);
    }

    @Override
    public Boolean insertAllSubTaskByParentTaskId(String subTaskName, int percentage, Date startDate, Date endDate, int handlerId, int projectId, int parentTaskId, char priority) {
        return subTaskRepository.insertAllSubTaskByParentTaskId(subTaskName, percentage, startDate, endDate, handlerId, projectId, parentTaskId, priority);
    }

    @Override
    public List<SubTaskIssueApiResponse> findAllIssueInEachProject(int projectId) {
        return subTaskRepository.findAllIssueInEachProject(projectId);
    }

    @Override
    public CountIssueInProjectApiResponse countIssueInProject(int projectId) {
        return subTaskRepository.countIssueInProject(projectId);
    }

    @Override
    public Boolean updateSubTaskToResolveIssue(int subTaskId) {
        return subTaskRepository.updateSubTaskToResolveIssue(subTaskId);
    }

    @Override
    public List<SubTaskIssueApiResponse> findAllIssueByParentTaskId(int parentTaskId) {
        return subTaskRepository.findAllIssueByParentTaskId(parentTaskId);
    }
}
