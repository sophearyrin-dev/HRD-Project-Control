//package com.kshrd.hrdprojectcontrolapi.services.projectdivision;
//
//import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;
//import com.kshrd.hrdprojectcontrolapi.repositories.projectdivision.ProjectDivisionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ScheduleTypeFilterDocumentAndProjectImp implements ScheduleTypeFilterDocumentAndProjectService{
//
//    private ProjectDivisionRepository projectDivisionRepository;
//
//    @Autowired
//    public ScheduleTypeFilterDocumentAndProjectImp(ProjectDivisionRepository projectDivisionRepository) {
//        this.projectDivisionRepository = projectDivisionRepository;
//    }
//
//    @Override
//    public ScheduleTypeFilterDocumentAndProject documentAndProjects(int scheduleTypeId) {
//        return projectDivisionRepository.documentAndProjects(scheduleTypeId);
//    }
//}
