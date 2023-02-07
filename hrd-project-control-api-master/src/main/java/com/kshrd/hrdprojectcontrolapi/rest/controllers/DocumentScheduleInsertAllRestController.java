package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.*;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.documentschedule.DocumentScheduleService;
import com.kshrd.hrdprojectcontrolapi.services.documenttopic.DocumentTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * This class use for insert a Document schedule and multi Document topic
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/document-schedule-insert-all")
@Api(tags = "Document schedule and document topic", value = "Document schedule and document topic", description = "Controller for Document schedule and document topic Control")

public class DocumentScheduleInsertAllRestController {

    private Validation validation;

    private MessageProperties message;

    DocumentScheduleService documentScheduleService;

    DocumentTopicService documentTopicService;

    @Autowired
    public DocumentScheduleInsertAllRestController(DocumentScheduleService documentScheduleService, DocumentTopicService documentTopicService) {
        this.documentScheduleService = documentScheduleService;
        this.documentTopicService = documentTopicService;
    }

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    /**
     * insert more document topic to a ducument schedule
     * @param documentScheduleAllInsertApiRequest
     * @return
     */

    @PostMapping()
    @ApiOperation("Insert document and multi topics")
    public ResponseEntity<BaseApiResponse<DocumentScheduleAllInsertApiRequest>> insertAll(@RequestBody DocumentScheduleAllInsertApiRequest documentScheduleAllInsertApiRequest )
    {

        //for show message
        String inserted=message.inserted("Document schedule and Multi document topics");
        String insertError=message.insertError("Document schedule and Multi document topics");
        String errorOperation=message.errorOperation("Document schedule and Multi document topics");

        BaseApiResponse<DocumentScheduleAllInsertApiRequest> response=new BaseApiResponse<>();

        try{

            DocumentSchedule documentSchedule=new DocumentSchedule();

            List<DocumentTopicApiRequest> documentTopicApiRequests=new ArrayList<>();

            DocumentScheduleApiRequest documentScheduleApiRequest=documentScheduleAllInsertApiRequest.getDocumentScheduleApiRequest();

            BeanUtils.copyProperties(documentScheduleApiRequest,documentSchedule);

            response.setData(documentScheduleAllInsertApiRequest);

            Boolean value=documentScheduleService.insertDocumentSchedule(documentSchedule);

            int id=documentSchedule.getId();

            List<DocumentTopicInsertAllApiRequest> results=documentScheduleAllInsertApiRequest.getDocumentTopicInsertAllApiRequests();

            for (DocumentTopicInsertAllApiRequest source:results) {

                DocumentTopicApiRequest target= new DocumentTopicApiRequest();
                BeanUtils.copyProperties(source , target);
                documentTopicApiRequests.add(target);

            }

            for (DocumentTopicApiRequest values:documentTopicApiRequests) {

                values.setProjectScheduleId(id);

                boolean checks=validation.checkInputString(values.getName());

                if(checks)
                {
                    response.setMessage("Document schedule invalid.");
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    return ResponseEntity.ok(response);
                }

                documentTopicService.insertDocumentTopic(values);

            }

            if(value)
            {
                response.setMessage(inserted);
                response.setStatus(HttpStatus.CREATED);
            }

        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
