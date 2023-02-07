package com.kshrd.hrdprojectcontrolapi.services.projecttopic;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.ProjectTopicRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectTopicApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTopicServiceImp implements ProjectTopicService{
    ProjectTopicRepository projectTopicRepository;
    @Autowired
    public ProjectTopicServiceImp(ProjectTopicRepository projectTopicRepository) {
        this.projectTopicRepository = projectTopicRepository;
    }

    @Override
    public Boolean deleteProjectTopic(int id) {
        return projectTopicRepository.deleteProjectTopic(id);
    }

    @Override
    public Boolean updateProjectTopic(ProjectTopicApiRequest projectTopicApiRequest) {
        return projectTopicRepository.updateProjectTopic(projectTopicApiRequest);
    }

    @Override
    public List<ProjectTopicApiResponse> findAllProjectTopic(int id) {
        return projectTopicRepository.findAllProjectTopic(id);
    }

    @Override
    public Boolean insertProjectTopic(ProjectTopicApiRequest projectTopicApiRequest) {
        return projectTopicRepository.insertProjectTopic(projectTopicApiRequest);
    }
}
