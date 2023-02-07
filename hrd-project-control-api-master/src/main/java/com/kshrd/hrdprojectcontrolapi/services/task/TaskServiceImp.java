package com.kshrd.hrdprojectcontrolapi.services.task;

import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressOfAllProjectFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskProgressFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.TaskUpdateFilter;
import com.kshrd.hrdprojectcontrolapi.repositories.task.TaskRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.TaskApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Boolean updateMainTaskHandler(TaskUpdateFilter taskUpdateFilter, TaskUpdateApiRequest taskUpdateApiRequest) {
        return taskRepository.updateMainTaskHandler(taskUpdateFilter, taskUpdateApiRequest);
    }

    @Override
    public List<ProgressOfAllMainTaskInProjectApiResponse> findProgressOfAllMainTaskByProjectId(TaskProgressFilter taskProgressFilter) {
        return taskRepository.findProgressOfAllMainTaskByProjectId(taskProgressFilter);
    }

    @Override
    public List<DocumentTaskPercentageApiResponse> findAllDocumentTaskPercentage(TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter) {
        return taskRepository.findAllDocumentTaskPercentage(taskProgressOfAllProjectFilter);
    }

    @Override
    public List<TaskProgressOfAllProjectApiResponse> findTaskProgressOfAllProject(TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter) {
        return taskRepository.findTaskProgressOfAllProject(taskProgressOfAllProjectFilter);
    }

    @Override
    public CountIssueInProjectApiResponse countIssueToShowInProject(int projectId) {
        return taskRepository.countIssueToShowInProject(projectId);
    }

    @Override
    public List<TaskProgressOfAllProjectApiResponse> findAllTaskProgressByProjectId(int projectId) {
        return taskRepository.findAllTaskProgressByProjectId(projectId);
    }

    @Override
    public List<DocumentTaskPercentageApiResponse> findDocumentTaskPercentageByProjectId(int projectId) {
        return taskRepository.findDocumentTaskPercentageByProjectId(projectId);
    }
}
