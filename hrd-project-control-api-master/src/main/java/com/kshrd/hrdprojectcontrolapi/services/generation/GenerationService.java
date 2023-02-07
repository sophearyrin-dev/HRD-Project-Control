package com.kshrd.hrdprojectcontrolapi.services.generation;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.rest.request.GenerationApiRequest;

import java.util.List;

public interface GenerationService {

    Boolean insertGeneration(GenerationApiRequest generationApiRequest);

    Boolean deleteGeneration(int id);

    Boolean updateGeneration(GenerationApiRequest generationApiRequest);

    List<Generation> findAllGeneration();
}
