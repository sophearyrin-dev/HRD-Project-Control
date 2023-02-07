package com.kshrd.hrdprojectcontrolapi.services.documenttopic;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentTopic;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentTopicApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DocumentTopicService {
    Boolean deleteDocumentTopic(int id);
    Boolean updateDocumentTopic(DocumentTopicApiRequest documentTopicApiRequest);
    List<DocumentTopicApiResponse> findAllDocumentTopic(int id);
    Boolean insertDocumentTopic(DocumentTopicApiRequest DocumentTopicApiRequest);
}
