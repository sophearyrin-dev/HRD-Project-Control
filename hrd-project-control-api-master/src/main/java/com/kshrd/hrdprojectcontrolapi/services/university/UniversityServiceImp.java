package com.kshrd.hrdprojectcontrolapi.services.university;

import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.repositories.users.UniversityRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.UniversityApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UniversityServiceImp implements UniversityService {
    UniversityRepository universityRepository;
    @Autowired
    public UniversityServiceImp(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }
    @Override
    public Boolean insert(UniversityApiRequest universityApiRequest) {
        return universityRepository.insert(universityApiRequest);
    }
    @Override
    public Boolean delete(int id) {
        return universityRepository.delete(id);
    }
    @Override
    public Boolean update(UniversityApiRequest universityApiRequest) {
        return universityRepository.update(universityApiRequest);
    }
    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }
}
