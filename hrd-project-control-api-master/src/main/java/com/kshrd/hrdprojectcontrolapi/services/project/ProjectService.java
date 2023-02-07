package com.kshrd.hrdprojectcontrolapi.services.project;

import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectApiFilterResponse;

import java.util.List;

public interface ProjectService {

    List<Project> findAllProject();

    Boolean insertProject(ProjectApiRequest projectApiRequest);

    Boolean updateProject(ProjectUpdateApiRequest projectUpdateApiRequest);

    Boolean deleteProject(int id);

    List<ProjectApiFilterResponse> findAllWithFilter(ProjectFilter filter);

    Project findOne(int id);

//    Boolean userActionDelete(UserActionDeleteApiRequest userActionDelete, int projectId);
}
