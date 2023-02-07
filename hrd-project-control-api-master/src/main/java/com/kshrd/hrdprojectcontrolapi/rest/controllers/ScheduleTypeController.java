package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilter;
import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;
import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleTypeApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UniversityApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import com.kshrd.hrdprojectcontrolapi.services.schedule.ScheduleTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class use for schedule type crud
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/schedule-type")
@Api(tags = "Schedule type", value = "Schedule type", description = "Controller for schedule type Control")

public class ScheduleTypeController {

    ScheduleTypeService scheduleTypeService;

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
    public ScheduleTypeController(ScheduleTypeService scheduleTypeService) {
        this.scheduleTypeService = scheduleTypeService;
    }

    /**
     * This method use for insert schedule topic
     * @param scheduleTypeApiRequest
     * @return
     */

    @PostMapping()
    @ApiOperation(value = "Insert schedule type")
    public ResponseEntity<BaseApiResponse<ScheduleTypeApiRequest>> insert(@RequestBody ScheduleTypeApiRequest scheduleTypeApiRequest)
    {

        //for show message
        String inserted=message.inserted("Schedule type");
        String insertError=message.insertError("Schedule type");
        String errorOperation=message.errorOperation("Schedule type");

        BaseApiResponse<ScheduleTypeApiRequest> response=new BaseApiResponse<>();

        try{

            boolean check=validation.checkInputString(scheduleTypeApiRequest.getName());

            if(check)
            {
                response.setMessage("Schedule type is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            Boolean value=scheduleTypeService.insertType(scheduleTypeApiRequest);
            response.setData(scheduleTypeApiRequest);

            if(value)
            {
                response.setMessage(inserted);
                response.setStatus(HttpStatus.CREATED);
            }
            else
            {
                response.setMessage(insertError);
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
     * This method use for delete schedule type by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete schedule type by id")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {
        //for show message
        String deleted=message.deleted("Schedule type");
        String deleteError=message.deleteError("Schedule type");
        String errorOperation=message.errorOperation("Schedule type");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try{

            Boolean value=scheduleTypeService.deleteType(id);

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
     * This method use for update schedule type by id
     * @param scheduleTypeApiRequest
     * @param typeId
     * @return
     */

    @PutMapping("/{typeId}")
    @ApiOperation(value = "Update schedule type by id")
    public ResponseEntity<BaseApiResponse<ScheduleTypeApiRequest>> update(@RequestBody ScheduleTypeApiRequest scheduleTypeApiRequest,@PathVariable int typeId)
    {

        //for show message
        String updated=message.updated("Schedule type");
        String updateError=message.updateError("Schedule type");
        String errorOperation=message.errorOperation("Schedule type");

        BaseApiResponse<ScheduleTypeApiRequest> response=new BaseApiResponse<>();

        try{

            boolean check=validation.checkInputString(scheduleTypeApiRequest.getName());

            if(check)
            {
                response.setMessage("Schedule type is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            scheduleTypeApiRequest.setTypeId(typeId);
            Boolean value=scheduleTypeService.updateType(scheduleTypeApiRequest);
            response.setData(scheduleTypeApiRequest);

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
     * This method use for get all schedule type
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "Show all schedule type")
    public ResponseEntity<BaseApiResponse<List<ScheduleTypeApiResponse>>> findAll()
    {
        //for show message
        String selected=message.selected("Schedule types");
        String dataEmpty=message.dataEmpty("Schedule type");
        String errorOperation=message.errorOperation("Schedule type");

        BaseApiResponse<List<ScheduleTypeApiResponse>> response=new BaseApiResponse<>();

        try {

            List<ScheduleTypeApiResponse> value = scheduleTypeService.findAllType();

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
            response.setMessage(e.getLocalizedMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    /**
     * -Get schedule type by Generation and course,
     * use class (DailyReportAPiFilterResponse)
     *      *       - view data by(ScheduleTypeApiFilterResponse )
     *              - filter data by class (ScheduleTypeFilterDocumentAndProjectService)
     *      *
     * @param scheduleTypeFilter
     * @return
     */
    @GetMapping("/filter")
    @ApiOperation("Show schedule type by generation and course")
    public ResponseEntity<BaseApiResponse<List<ScheduleTypeApiFilterResponse>>> FilterByCourseAndGenderation(ScheduleTypeFilter scheduleTypeFilter) {

        String selected=message.selected("Schedule types");
        String dataEmpty=message.dataEmpty("Schedule type");
        String errorOperation=message.errorOperation("Schedule type");


        BaseApiResponse<List<ScheduleTypeApiFilterResponse>> response = new BaseApiResponse<>();
        try {
            List<ScheduleTypeApiFilterResponse> value = scheduleTypeService.findAllWithFilter(scheduleTypeFilter);
            if(!value.isEmpty())
            {
                if(value.size()==1){
                    selected=message.selectedOne("Schedule type");
                }
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
