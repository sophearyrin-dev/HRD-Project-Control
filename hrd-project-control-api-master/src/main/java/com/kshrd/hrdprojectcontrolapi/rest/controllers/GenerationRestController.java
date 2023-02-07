package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.GenerationApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.generation.GenerationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class use for generation crud
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/generations")
@Api(tags = "Generation", value = "Generation", description = "Controller for Generation Control")

public class GenerationRestController {

    private Validation validation;

    private MessageProperties message;

    GenerationService generationService;

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }
    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public GenerationRestController(GenerationService generationService) {
        this.generationService = generationService;
    }

    /**
     * This method use for insert generation
     * @param generationApiRequest
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "Insert generation")
    public ResponseEntity<BaseApiResponse<GenerationApiRequest>> insertGeneration(@RequestBody GenerationApiRequest generationApiRequest)
    {
        //for show message
        String inserted=message.inserted("Generations");
        String insertError=message.insertError("Generations");
        String errorOperation=message.errorOperation("Generations");

        BaseApiResponse<GenerationApiRequest> response=new BaseApiResponse<>();

        try {

            //check generation
            boolean check=validation.checkInputString(generationApiRequest.getName());

            if(check)
            {
                response.setMessage("Generation is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            Boolean value = generationService.insertGeneration(generationApiRequest);
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

        response.setData(generationApiRequest);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    /**
     * This method use for delete generation by id
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete generation by id")
    public ResponseEntity<BaseApiResponse<String>> deleteGeneration(@PathVariable int id)
    {

        //for show message
        String deleted=message.deleted("Generations");
        String deleteError=message.deleteError("Generations");
        String errorOperation=message.errorOperation("Generations");

        BaseApiResponse<String> response=new BaseApiResponse<>();
        try {
            Boolean value = generationService.deleteGeneration(id);
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
     * This method use for update generation by id
     * @param generationApiRequest
     * @return
     */

    @PutMapping("/{generationId}")
    @ApiOperation(value = "Update generation by id")
    public ResponseEntity<BaseApiResponse<GenerationApiRequest>> updateGeneration(@RequestBody GenerationApiRequest generationApiRequest,@PathVariable int generationId)
    {

        //for show message
        String updated=message.updated("Generation");
        String updateError=message.updateError("Generation");
        String errorOperation=message.errorOperation("Generation");

        BaseApiResponse<GenerationApiRequest> response=new BaseApiResponse<>();

        try{

            //check generation
            boolean check=validation.checkInputString(generationApiRequest.getName());

            if(check)
            {
                response.setMessage("Generation is invalid.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            generationApiRequest.setGenerationId(generationId);
            Boolean value= generationService.updateGeneration(generationApiRequest);

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

        response.setData(generationApiRequest);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * This method use for find all generation
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "Show all generation")
    public ResponseEntity<BaseApiResponse<List<Generation>>> findAllGeneration()
    {

        //for show message
        String selected=message.selected("Generations");
        String dataEmpty=message.dataEmpty("Generation");
        String errorOperation=message.errorOperation("Generation");

        BaseApiResponse<List<Generation>> response=new BaseApiResponse<>();

        try {

            List<Generation> value = generationService.findAllGeneration();

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
