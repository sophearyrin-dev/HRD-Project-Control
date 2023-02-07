package com.kshrd.hrdprojectcontrolapi.services.projectdivision;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectDivision;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;

import java.util.Date;
import java.util.List;

public interface ProjectDivisionService {

    List<ProjectDivisionApiResponse> findAllUserByProjectId(int id);
    List<Project> findProjectDivisionByProjectId(int id);
    List<ProjectUserApiResponse> findProjectDivisionByUserId(int id);
    List<ProjectMainTaskApiResponse> findAllProjectMainTask(int scheduleTypeId);
    List<DocumentTaskApiResponse> findAllDocumentTask(int scheduleTypeId);
    DocumentTaskDeadlineApiResponse findDocumentTaskDeadlineByScheduleTypeId(int scheduleTypeId);
    int findIdOfDocumentTaskByProjectId(int projectId);
    List<Integer> findAllIdOfParentTaskByProjectId(int projectId);
    Boolean insertDefaultSubTaskToEachParentByProjectId(String defaultSubTaskName, int percentage, int parentTaskId, int projectId);
    Boolean insertMainTask(String taskName, Date startDate, Date endDate, int projectId);
    Boolean insertDocumentSubTask(String taskName, int percentage, Date startDate, Date endDate, int parentTaskId, int projectId);

    Boolean insertUserDivision(ProjectDivision projectDivision1);
    ProjectDivision findOne(int proId,int userId);

    CheckProject findProjectId(int proId);
    Boolean deleteProjectDivision(int Project_division_id);
}
