package com.kshrd.hrdprojectcontrolapi.services.projecttopic;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectTopicApiResponse;

import java.util.List;

public interface ProjectTopicService {
    Boolean deleteProjectTopic(int id);
    Boolean updateProjectTopic(ProjectTopicApiRequest projectTopicApiRequest);
    List<ProjectTopicApiResponse> findAllProjectTopic(int id);
    Boolean insertProjectTopic(ProjectTopicApiRequest projectTopicApiRequest);
}
