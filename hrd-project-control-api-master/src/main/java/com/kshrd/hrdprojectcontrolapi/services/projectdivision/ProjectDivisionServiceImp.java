package com.kshrd.hrdprojectcontrolapi.services.projectdivision;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectDivision;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;
import com.kshrd.hrdprojectcontrolapi.repositories.projectdivision.ProjectDivisionRepository;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectDivisionServiceImp implements ProjectDivisionService,ScheduleTypeFilterDocumentAndProjectService{

    private ProjectDivisionRepository projectDivisionRepository;

    @Autowired
    public ProjectDivisionServiceImp(ProjectDivisionRepository projectDivisionRepository) {
        this.projectDivisionRepository = projectDivisionRepository;
    }

    @Override
    public CheckProject findProjectId(int proId) {
        return projectDivisionRepository.findProjectId(proId);
    }

    @Override
    public List<ProjectDivisionApiResponse> findAllUserByProjectId(int id) {
        return this.projectDivisionRepository.findAllUserByProjectId(id);
    }

    @Override
    public List<Project> findProjectDivisionByProjectId(int id) {
        return this.projectDivisionRepository.findProjectDivisionByProjectId(id);
    }

    @Override
    public List<ProjectUserApiResponse> findProjectDivisionByUserId(int id) {
        return this.projectDivisionRepository.findProjectDivisionByUserId(id);
    }

    @Override
    public Boolean insertUserDivision(ProjectDivision projectDivision1) {
        return this.projectDivisionRepository.insertUserDivision(projectDivision1);
    }

    @Override
    public ProjectDivision findOne(int proId, int userId) {
        return projectDivisionRepository.findOne(proId,userId);
    }


    @Override
    public ScheduleTypeFilterDocumentAndProject documentAndProjects(int scheduleTypeId) {
        return this.projectDivisionRepository.documentAndProjects(scheduleTypeId);
    }

    @Override
    public List<ProjectMainTaskApiResponse> findAllProjectMainTask(int scheduleTypeId) {
        return this.projectDivisionRepository.findAllProjectMainTask(scheduleTypeId);
    }

    @Override
    public List<Integer> findAllIdOfParentTaskByProjectId(int projectId) {
        return this.projectDivisionRepository.findAllIdOfParentTaskByProjectId(projectId);
    }

    @Override
    public Boolean insertDefaultSubTaskToEachParentByProjectId(String defaultSubTaskName, int percentage, int parentTaskId, int projectId) {
        return this.projectDivisionRepository.insertDefaultSubTaskToEachParentByProjectId(defaultSubTaskName, percentage, parentTaskId, projectId);
    }

    @Override
    public List<DocumentTaskApiResponse> findAllDocumentTask(int scheduleTypeId) {
        return this.projectDivisionRepository.findAllDocumentTask(scheduleTypeId);
    }

    @Override
    public DocumentTaskDeadlineApiResponse findDocumentTaskDeadlineByScheduleTypeId(int scheduleTypeId) {
        return this.projectDivisionRepository.findDocumentTaskDeadlineByScheduleTypeId(scheduleTypeId);
    }

    @Override
    public int findIdOfDocumentTaskByProjectId(int projectId) {
        return this.projectDivisionRepository.findIdOfDocumentTaskByProjectId(projectId);
    }

    @Override
    public Boolean insertMainTask(String taskName, Date startDate, Date endDate, int projectId) {
        return this.projectDivisionRepository.insertMainTask(taskName, startDate, endDate, projectId);
    }

    @Override
    public Boolean insertDocumentSubTask(String taskName, int percentage, Date startDate, Date endDate, int parentTaskId, int projectId) {
        return this.projectDivisionRepository.insertDocumentSubTask(taskName, percentage, startDate, endDate, parentTaskId, projectId);
    }

    @Override
    public Boolean deleteProjectDivision(int Project_division_id) {
        return this.projectDivisionRepository.deleteProjectDivision(Project_division_id);
    }
}
