package com.kshrd.hrdprojectcontrolapi.services.projectdivision;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;

import java.util.List;

public interface ScheduleTypeFilterDocumentAndProjectService {

    ScheduleTypeFilterDocumentAndProject documentAndProjects(int scheduleTypeId);
}
