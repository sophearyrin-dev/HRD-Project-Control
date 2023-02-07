package com.kshrd.hrdprojectcontrolapi.services.project;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.repositories.project.ProjectRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectApiFilterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService{

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAllProject() {

        return this.projectRepository.findAllProject();
    }

    @Override
    public Boolean insertProject(ProjectApiRequest projectApiRequest) {

        return this.projectRepository.insertProject(projectApiRequest);
    }

    @Override
    public Boolean updateProject(ProjectUpdateApiRequest projectUpdateApiRequest) {
        return this.projectRepository.updateProject(projectUpdateApiRequest);
    }

    @Override
    public Boolean deleteProject(int id) {
        return this.projectRepository.deleteProject(id);
    }


    @Override
    public List<ProjectApiFilterResponse> findAllWithFilter(ProjectFilter filter) {
        return this.projectRepository.findAllWithFilter(filter);
    }

    @Override
    public Project findOne(int id) {
        return this.projectRepository.findOne(id);
    }

//    @Override
//    public Boolean userActionDelete(UserActionDeleteApiRequest userActionDelete, int projectId) {
//        return this.projectRepository.actionUser(userActionDelete, projectId);
//    }
}
