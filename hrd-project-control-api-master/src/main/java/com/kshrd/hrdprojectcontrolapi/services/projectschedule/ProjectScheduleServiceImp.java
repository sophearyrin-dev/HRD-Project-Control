package com.kshrd.hrdprojectcontrolapi.services.projectschedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.ProjectScheduleRepostitory;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleApiRequst;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectScheduleApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectScheduleServiceImp implements ProjectScheduleService {
    ProjectScheduleRepostitory projectScheduleRepostitory;
    @Autowired
    public ProjectScheduleServiceImp(ProjectScheduleRepostitory projectScheduleRepostitory) {
        this.projectScheduleRepostitory = projectScheduleRepostitory;
    }

    @Override
    public Boolean insertProjectSchedule(ProjectSchedule projectSchedule) {
        return projectScheduleRepostitory.insertProjectSchedule(projectSchedule);
    }

    @Override
    public Boolean deleteProjectSchedule(int id) {
        return projectScheduleRepostitory.deleteProjectSchedule(id);
    }

    @Override
    public Boolean updateStatusProjectSchedule(ScheduleAction scheduleAction, int id) {
        return projectScheduleRepostitory.updateStatusProjectSchedule(scheduleAction, id);
    }

    @Override
    public Boolean updateProjectSchedule(ProjectScheduleApiRequst projectScheduleApiRequst) {
        return projectScheduleRepostitory.updateProjectSchedule(projectScheduleApiRequst);
    }

    @Override
    public List<ProjectScheduleApiResponse> findAllProjectSchedule() {
        return projectScheduleRepostitory.findAllProjectSchedule();
    }

    @Override
    public ProjectScheduleApiResponse findOneProjectSchedule(int id) {
        return projectScheduleRepostitory.findOneProjectSchedule(id);
    }
}
