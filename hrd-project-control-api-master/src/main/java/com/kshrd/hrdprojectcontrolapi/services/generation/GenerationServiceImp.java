package com.kshrd.hrdprojectcontrolapi.services.generation;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.repositories.users.GenerationRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.GenerationApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenerationServiceImp implements GenerationService {

    GenerationRepository generationRepository;
    @Autowired
    public GenerationServiceImp(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }
    @Override
    public Boolean insertGeneration(GenerationApiRequest generationApiRequest) {
        return generationRepository.insertGeneration(generationApiRequest);
    }
    @Override
    public Boolean deleteGeneration(int id) {
        return generationRepository.deleteGeneration(id);
    }

    @Override
    public Boolean updateGeneration(GenerationApiRequest generationApiRequest) {
        return generationRepository.updateGeneration(generationApiRequest);
    }
    @Override
    public List<Generation> findAllGeneration() {
        return generationRepository.findAllGeneration();
    }
}
