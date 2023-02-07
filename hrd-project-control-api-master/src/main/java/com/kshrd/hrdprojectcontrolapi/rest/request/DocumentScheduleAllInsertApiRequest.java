package com.kshrd.hrdprojectcontrolapi.rest.request;

import java.util.List;

public class DocumentScheduleAllInsertApiRequest {

    DocumentScheduleApiRequest documentScheduleApiRequest;
    List<DocumentTopicInsertAllApiRequest> documentTopicInsertAllApiRequests;

    public DocumentScheduleAllInsertApiRequest() {
    }

    public DocumentScheduleAllInsertApiRequest(DocumentScheduleApiRequest documentScheduleApiRequest, List<DocumentTopicInsertAllApiRequest> documentTopicInsertAllApiRequests) {
        this.documentScheduleApiRequest = documentScheduleApiRequest;
        this.documentTopicInsertAllApiRequests = documentTopicInsertAllApiRequests;
    }

    public DocumentScheduleApiRequest getDocumentScheduleApiRequest() {
        return documentScheduleApiRequest;
    }

    public void setDocumentScheduleApiRequest(DocumentScheduleApiRequest documentScheduleApiRequest) {
        this.documentScheduleApiRequest = documentScheduleApiRequest;
    }

    public List<DocumentTopicInsertAllApiRequest> getDocumentTopicInsertAllApiRequests() {
        return documentTopicInsertAllApiRequests;
    }

    public void setDocumentTopicInsertAllApiRequests(List<DocumentTopicInsertAllApiRequest> documentTopicInsertAllApiRequests) {
        this.documentTopicInsertAllApiRequests = documentTopicInsertAllApiRequests;
    }

    @Override
    public String toString() {
        return "DocumentScheduleAllInsertApiRequest{" +
                "documentScheduleApiRequest=" + documentScheduleApiRequest +
                ", documentTopicInsertAllApiRequests=" + documentTopicInsertAllApiRequests +
                '}';
    }
}
