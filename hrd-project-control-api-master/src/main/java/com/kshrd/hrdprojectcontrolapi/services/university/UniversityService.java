package com.kshrd.hrdprojectcontrolapi.services.university;

import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.rest.request.UniversityApiRequest;

import java.util.List;

public interface UniversityService {
    Boolean insert(UniversityApiRequest universityApiRequest);
    Boolean delete(int id);
    Boolean update(UniversityApiRequest universityApiRequest);
    List<University> findAll();
}
