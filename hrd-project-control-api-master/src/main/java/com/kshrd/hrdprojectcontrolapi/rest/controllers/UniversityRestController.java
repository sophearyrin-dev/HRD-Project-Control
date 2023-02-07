package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.UniversityApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.UserApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.university.UniversityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for university crud
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/universities")
@Api(tags = "University", value = "University", description = "Controller for University Control")

public class UniversityRestController {

    UniversityService universityService;

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
    public UniversityRestController(UniversityService universityService) {
        this.universityService = universityService;
    }

    /**
     * This method use for insert university
     * @param universityApiRequest
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "Insert university")
    public ResponseEntity<BaseApiResponse<UniversityApiRequest>> insert(@RequestBody UniversityApiRequest universityApiRequest)
    {

        //for show message
        String inserted=message.inserted("Universities");
        String insertError=message.insertError("Universities");
        String errorOperation=message.errorOperation("Universities");

        BaseApiResponse<UniversityApiRequest> response=new BaseApiResponse<>();

        try {

            //check university
            boolean check=validation.checkInputString(universityApiRequest.getName());
            response.setData(universityApiRequest);

            if(check)
            {
                response.setMessage("University is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            Boolean value = universityService.insert(universityApiRequest);
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
     * This method use for delete university by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete university by id")
    public ResponseEntity<BaseApiResponse<String>> delete(@PathVariable int id)
    {

        //for show message
        String deleted=message.deleted("Universities");
        String deleteError=message.deleteError("Universities");
        String errorOperation=message.errorOperation("Universities");

        BaseApiResponse<String> response=new BaseApiResponse<>();

        try{

            Boolean value=universityService.delete(id);

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
     * This method use for update university by id
     * @param universityApiRequest
     * @return
     */
    @PutMapping("/{universityId}")
    @ApiOperation(value = "Update university by id")
    public ResponseEntity<BaseApiResponse<UniversityApiRequest>> update(@PathVariable int universityId,@RequestBody UniversityApiRequest universityApiRequest)
    {

        //for show message
        String updated=message.updated("Universities");
        String updateError=message.updateError("Universities");
        String errorOperation=message.errorOperation("Universities");

        BaseApiResponse<UniversityApiRequest> response=new BaseApiResponse<>();

        try {

            //check university
            boolean check=validation.checkInputString(universityApiRequest.getName());
            response.setData(universityApiRequest);

            if(check)
            {
                response.setMessage("University is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            universityApiRequest.setUniversityId(universityId);
            Boolean value = universityService.update(universityApiRequest);
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
     * This method use for get all university data
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "Show all university")
    public ResponseEntity<BaseApiResponse<List<University>>> findAll()
    {

        //for show message
        String selected=message.selected("Universities");
        String dataEmpty=message.dataEmpty("Universities");
        String errorOperation=message.errorOperation("Universities");

        BaseApiResponse<List<University>> response=new BaseApiResponse<>();

        try {
            List<University> values = universityService.findAll();
            if(!values.isEmpty())
            {
                response.setMessage(selected);
                response.setData(values);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(dataEmpty);
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
