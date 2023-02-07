package com.kshrd.hrdprojectcontrolapi.services.projectschedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleApiRequst;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectScheduleApiResponse;

import java.util.List;

public interface ProjectScheduleService {

    Boolean insertProjectSchedule(ProjectSchedule projectSchedule);

    Boolean deleteProjectSchedule(int id);

    Boolean updateStatusProjectSchedule(ScheduleAction scheduleAction,  int id);

    Boolean updateProjectSchedule(ProjectScheduleApiRequst projectScheduleApiRequst);

    List<ProjectScheduleApiResponse> findAllProjectSchedule();

    ProjectScheduleApiResponse findOneProjectSchedule(int id);
}
