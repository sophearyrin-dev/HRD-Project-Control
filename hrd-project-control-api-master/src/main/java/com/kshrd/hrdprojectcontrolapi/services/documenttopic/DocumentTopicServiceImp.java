package com.kshrd.hrdprojectcontrolapi.services.documenttopic;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentTopic;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.DocumentTopicRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentTopicApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DocumentTopicServiceImp implements DocumentTopicService {
    DocumentTopicRepository documentTopicRepository;
    @Autowired
    public DocumentTopicServiceImp(DocumentTopicRepository documentTopicRepository) {
        this.documentTopicRepository = documentTopicRepository;
    }

    @Override
    public Boolean deleteDocumentTopic(int id) {
        return documentTopicRepository.deleteDocumentTopic(id);
    }

    @Override
    public Boolean updateDocumentTopic(DocumentTopicApiRequest documentTopicApiRequest) {
        return documentTopicRepository.updateDocumentTopic(documentTopicApiRequest);
    }

    @Override
    public List<DocumentTopicApiResponse> findAllDocumentTopic(int id) {
        return documentTopicRepository.findAllDocumentTopic(id);
    }

    @Override
    public Boolean insertDocumentTopic(DocumentTopicApiRequest documentTopicApiRequest) {
        return documentTopicRepository.insertDocumentTopic(documentTopicApiRequest);
    }
}
