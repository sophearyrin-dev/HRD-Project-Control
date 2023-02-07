package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentTopic;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentTopicApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.documenttopic.DocumentTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for document topic crud(insert,delete,update,post)
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/document-schedule-topic")
@Api(tags = "Document topic", value = "Document topic", description = "Controller for Document topic Control")

public class DocumentTopicRestController {

        DocumentTopicService documentTopicService;

        private Validation validation;

        private MessageProperties message;

        @Autowired
        public void setValidation(Validation validation) {
        this.validation = validation;
    }

        @Autowired
        public void setMessage(MessageProperties message) {
        this.message = message;
    }

        @Autowired
        public DocumentTopicRestController(DocumentTopicService documentTopicService) {
            this.documentTopicService = documentTopicService;
        }

        /**
         * This method use for insert document topic
         * @param documentTopicApiRequest
         * @return
         */

//        @PostMapping()
//        @ApiOperation(value = "Insert document topic")
//        public ResponseEntity<BaseApiResponse<DocumentTopicApiRequest>> insert(@RequestBody DocumentTopicApiRequest documentTopicApiRequest)
//        {
//
//            //for show message
//            String inserted=message.inserted("Document topic");
//            String insertError=message.insertError("Document topic");
//            String errorOperation=message.errorOperation("Document topic");
//
//            BaseApiResponse<DocumentTopicApiRequest> response=new BaseApiResponse<>();
//
//            try {
//
//                boolean check=validation.checkInputString(documentTopicApiRequest.getName());
//                response.setData(documentTopicApiRequest);
//
//                if(check)
//                {
//                    response.setMessage("Document topic is invalid.");
//                    response.setStatus(HttpStatus.BAD_REQUEST);
//                    return ResponseEntity.ok(response);
//                }
//
//                Boolean value=documentTopicService.insertDocumentTopic(documentTopicApiRequest);
//
//                if(value)
//                {
//                    response.setMessage(inserted);
//                    response.setStatus(HttpStatus.CREATED);
//                }
//                else
//                {
//                    response.setMessage(insertError);
//                    response.setStatus(HttpStatus.BAD_REQUEST);
//                }
//            }catch (Exception e) {
//                response.setMessage(errorOperation);
//                response.setStatus(HttpStatus.BAD_REQUEST);
//            }
//
//            response.setTimestamp(new Timestamp(System.currentTimeMillis()));
//            return ResponseEntity.ok(response);
//        }

    /**
     * This method use for delete document topic by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete document topic")
        public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
        {

            //for show message
            String deleted=message.deleted("Document topic");
            String deleteError=message.deleteError("Document topic");
            String errorOperation=message.errorOperation("Document topic");

            BaseApiResponse<String> response=new BaseApiResponse<>();

            try {

                Boolean value=documentTopicService.deleteDocumentTopic(id);

                if(value)
                {
                    response.setMessage(deleted);
                    response.setStatus(HttpStatus.OK);
                }
                else
                {
                    response.setMessage(deleteError);
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }
            }catch (Exception e)
            {
                response.setMessage(errorOperation);
                response.setStatus(HttpStatus.BAD_REQUEST);
            }

            response.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(response);
        }

    /**
     * This method use for update document schedule by id
     * @param documentTopicApiRequest
     * @return
     */

    @PutMapping("/{id}")
    @ApiOperation(value = "Update document topic")
        public ResponseEntity<BaseApiResponse<DocumentTopicApiRequest>> update(@RequestBody DocumentTopicApiRequest documentTopicApiRequest,@PathVariable int id)
        {

            //for show message
            String updated=message.updated("Document topic");
            String updateError=message.updateError("Document topic");
            String errorOperation=message.errorOperation("Document topic");

            BaseApiResponse<DocumentTopicApiRequest> response=new BaseApiResponse<>();

            try {

                boolean check=validation.checkInputString(documentTopicApiRequest.getName());
                response.setData(documentTopicApiRequest);

                if(check)
                {
                    response.setMessage("Document topic is invalid.");
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    return ResponseEntity.ok(response);
                }

                documentTopicApiRequest.setId(id);
                Boolean value=documentTopicService.updateDocumentTopic(documentTopicApiRequest);

                if(value)
                {
                    response.setMessage(updated);
                    response.setStatus(HttpStatus.OK);
                }
                else
                {
                    response.setMessage(updateError);
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }
            }catch (Exception e)
            {
                response.setMessage(e.getMessage());
                response.setStatus(HttpStatus.BAD_REQUEST);
            }

            response.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(response);
        }

    /**
     * This method use for find document topic by id
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Show document topic by id")
        public ResponseEntity<BaseApiResponse<List<DocumentTopicApiResponse>>> findAll(@PathVariable int id)
        {

            //for show message
            String selected=message.selected("Document topics");
            String dataEmpty=message.dataEmpty("Document topic");
            String errorOperation=message.errorOperation("Document topic");

            BaseApiResponse<List<DocumentTopicApiResponse>> response=new BaseApiResponse<>();
            DocumentSchedule documentSchedule = new DocumentSchedule();

            try {

                List<DocumentTopicApiResponse> value=documentTopicService.findAllDocumentTopic(id);

                if(!value.isEmpty())
                {
                    response.setMessage(selected);
                    response.setData(value);
                    response.setStatus(HttpStatus.OK);
                }
                else
                {
                    response.setMessage(dataEmpty);
                    response.setData(value);
                    response.setStatus(HttpStatus.NO_CONTENT);
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
