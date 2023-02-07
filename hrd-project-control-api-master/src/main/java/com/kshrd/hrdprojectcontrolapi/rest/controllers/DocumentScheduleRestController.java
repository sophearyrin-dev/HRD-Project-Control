package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentScheduleApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentScheduleApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.documentschedule.DocumentScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for document schedule can be(delete,update,get)
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/document-schedule")
@Api(tags = "Document schedule", value = "Document schedule", description = "Controller for document schedule Control")

public class DocumentScheduleRestController {

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

    DocumentScheduleService documentScheduleService;

    @Autowired
    public DocumentScheduleRestController(DocumentScheduleService documentScheduleService) {
        this.documentScheduleService = documentScheduleService;
    }

    /**
     * This method use for delete document schedule by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete document schedule by id")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {

        //for show message
        String deleted=message.deleted("Document schedule");
        String deleteError=message.deleteError("Document schedule");
        String errorOperation=message.errorOperation("Document schedule");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try{

            Boolean value=documentScheduleService.deleteDocumentSchedule(id);

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
     * @param documentScheduleApiRequest
     * @param id
     * @return
     */

    @PutMapping("/{id}")
    @ApiOperation(value="Update document schedule by schedule id")
    public ResponseEntity<BaseApiResponse<DocumentScheduleApiRequest>> update(@RequestBody DocumentScheduleApiRequest documentScheduleApiRequest,@PathVariable int id)
    {

        //for show message
        String updated=message.updated("Document schedule");
        String updateError=message.updateError("Document schedule");
        String errorOperation=message.errorOperation("Document schedule");

        BaseApiResponse<DocumentScheduleApiRequest> response=new BaseApiResponse<>();

        try{

            documentScheduleApiRequest.setId(id);
            Boolean value=documentScheduleService.updateDocumentSchedule(documentScheduleApiRequest);
            response.setData(documentScheduleApiRequest);

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
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method use for delete project topic by id
     * @param scheduleAction
     * @return
     */

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update status document schedule by schedule id ")
    public ResponseEntity<BaseApiResponse<String>> update(@RequestBody ScheduleAction scheduleAction, @PathVariable  int id)
    {

        //for show message
        String updated=message.updated("Document schedule");
        String updateError=message.updateError("Document schedule");
        String errorOperation=message.errorOperation("Document schedule");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try {
            scheduleAction.setId(id);
            Boolean value=documentScheduleService.updateStatusDocumentSchedule(scheduleAction, scheduleAction.getId());

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
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method use for get document schedule
     * @return
     */

    @GetMapping()
    @ApiOperation(value="Show All document schedules")
    public ResponseEntity<BaseApiResponse<List<DocumentScheduleApiResponse>>> findAll()
    {

        //for show message
        String selected=message.selected("Document schedules");
        String dataEmpty=message.dataEmpty("Document schedule");
        String errorOperation=message.errorOperation("Document schedule");

        BaseApiResponse<List<DocumentScheduleApiResponse>> response=new BaseApiResponse<>();

        try{

            List<DocumentScheduleApiResponse> value=documentScheduleService.findAllDocumentSchedule();

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


    @GetMapping("/{id}")
    @ApiOperation(value="Show One document schedules")
    public ResponseEntity<BaseApiResponse<DocumentScheduleApiResponse>> findOne(@PathVariable int id)
    {

        //for show message
        String selected=message.selected("Document schedules");
        String dataEmpty=message.dataEmpty("Document schedule");
        String errorOperation=message.errorOperation("Document schedule");

        BaseApiResponse<DocumentScheduleApiResponse> response=new BaseApiResponse<>();

        try{

            DocumentScheduleApiResponse value=documentScheduleService.findOneDocumentSchedule(id);

            if(value!=null)
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
