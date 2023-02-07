package com.kshrd.hrdprojectcontrolapi.rest.controllers;
import com.kshrd.hrdprojectcontrolapi.configurations.JwtConfigurations.JwtTokenUtil;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.users.User;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserUpdateApiResquest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.UserApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.users.UserService;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.security.AlgorithmConstraints;
import java.security.Key;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *      This rest controller is used for user CRUD
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/users")
@Api(tags = "User", value = "User", description = "Controller for User Control")
public class UserRestController {

    private static final String ALGO = "AES";

    private Validation validation;

    private MessageProperties message;

    BCryptPasswordEncoder encoder;

    private AuthenticationManager authenticationManager;

    JwtTokenUtil jwtTokenUtil;

    UserService userService;

    @Autowired
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * This method is used for getting all users
     * and get all users by generation Id
     * @param generationId
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "Show all users", response = UserApiResponse.class)
    public ResponseEntity<BaseApiResponse<List<UserApiResponse>>> findAllUsers(@RequestParam(value = "generationId", required = false, defaultValue = "0") int generationId)
    {
        //for show message
        String selected= message.selected("Users");
        String dataEmpty=message.dataEmpty(Resources.USER.getName());
        String errorOperation=message.errorOperation(Resources.USER.getName());
        String idNotFound = message.idNotFound("Generation");

        BaseApiResponse<List<UserApiResponse>> response=new BaseApiResponse<>();

        UserApiResponse checkUserId = new UserApiResponse();

        try {
            List<UserApiResponse> value=userService.findAllUsers(generationId);
            if(!value.isEmpty()) //have data
            {
                //check if have only one user
                if(value.size()==1){
                    selected=message.selectedOne("User");
                }
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);

            }
            else if(generationId != checkUserId.getUserId() ){ //no generation id in list
                response.setMessage(idNotFound);
                response.setStatus(HttpStatus.NOT_FOUND);
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


    /**
     * This method is used for inserting user information
     * @param userApiRequest
     * @return object userApiRequest
     */
    @PostMapping("")
    @ApiOperation(value = "Insert user", response = UserApiRequest.class)
    public ResponseEntity<BaseApiResponse<UserApiRequest>> insertUser(@RequestBody UserApiRequest userApiRequest)
    {
        //for show message
        String inserted=message.inserted(Resources.USER.getName());
        String insertError=message.insertError(Resources.USER.getName());
        String errorOperation=message.errorOperation(Resources.USER.getName());
        BaseApiResponse<UserApiRequest> response=new BaseApiResponse<>();
        try {
            //check user
            boolean username=validation.checkInputString(userApiRequest.getUsername());
            boolean password=validation.checkInputPassword(userApiRequest.getPassword());
            if(username||password)
            {
                if(username)
                {
                    if(password)
                        response.setMessage("Username and password are invalid.");
                    else
                        response.setMessage("Username is invalid.");
                }
                else
                {
                    response.setMessage("Password should have at least 8 characters.");
                }
                response.setData(userApiRequest);
                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }

            userApiRequest.setPassword(encoder.encode(userApiRequest.getPassword()));
            Boolean value=userService.insertUser(userApiRequest);

            if(value)
            {
                response.setMessage(inserted);
                response.setData(userApiRequest);
                response.setStatus(HttpStatus.CREATED);
            }
            else
            {
                response.setMessage(insertError);
                response.setData(userApiRequest);
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
     * This method is used for update user by user id
     * and validation on username, password
     * @param userUpdateApiResquest
     * @param userId
     * @return
     */
    @PutMapping("/{userId}")
    @ApiOperation(value = "Update user by user id")
    public ResponseEntity<BaseApiResponse<UserUpdateApiResquest>> updateUser(@RequestBody UserUpdateApiResquest userUpdateApiResquest, @PathVariable int userId)
    {
        //for show message
        String updated=message.updated(Resources.USER.getName());
        String updateError=message.updateError(Resources.USER.getName());
        String errorOperation=message.errorOperation(Resources.USER.getName());

        BaseApiResponse<UserUpdateApiResquest> response=new BaseApiResponse<>();

        try {

            //check user
            boolean username=validation.checkInputString(userUpdateApiResquest.getUsername());
            boolean password=validation.checkInputPassword(userUpdateApiResquest.getPassword());

            if(username||password)
            {
                if(username)
                {
                    if(password)
                        response.setMessage("Username and password are invalid.");
                    else
                        response.setMessage("Username is invalid.");
                }
                else
                {
                    response.setMessage("Password should have at least 8 characters.");
                }

                response.setData(userUpdateApiResquest);
                response.setStatus(HttpStatus.CREATED);
                return ResponseEntity.ok(response);
            }

            userUpdateApiResquest.setPassword(encoder.encode(userUpdateApiResquest.getPassword()));
            userUpdateApiResquest.setUserId(userId);
            Boolean value=userService.updateUser(userUpdateApiResquest, userUpdateApiResquest.getUserId());

            if(value)
            {
                response.setMessage(updated);
                response.setStatus(HttpStatus.OK);
                response.setData(userUpdateApiResquest);
            }
            else
            {
                response.setMessage(updateError);
                response.setData(userUpdateApiResquest);
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
     * Update user status by user id
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Update status user by user id")
    public ResponseEntity<BaseApiResponse<UserActionDeleteApiRequest>> updateUser(@PathVariable int userId)
    {
        UserActionDeleteApiRequest userActionDelete = new UserActionDeleteApiRequest();
        //for show message
        String deleted=message.deleted("User");
        String deletedError=message.deleteError("User");
        String errorOperation=message.errorOperation("User");

        BaseApiResponse<UserActionDeleteApiRequest> response=new BaseApiResponse<>();
        try {
            userActionDelete.setId(userId);
            Boolean value=userService.userActionDelete(userActionDelete, userActionDelete.getId());
            if(value)
            {
                response.setMessage(deleted);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(deletedError);
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


    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>>GetById(@PathVariable int id){

        String selected=message.selected("User");
        String dataEmpty=message.dataEmpty("Project");
        String dataNotFound = message.idNotFound("Project");
        String errorOperation=message.errorOperation("Project");

        Map<String, Object>res = new HashMap<>();
        try {
            UserApiResponse b = userService.findOne(id);
            if (b == null) {
                res.put("message", errorOperation);
                return ResponseEntity.notFound().build();
            }
            else
            {
                res.put("message", selected);
                res.put("data", b);
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }
        catch (Exception e)
        {
            res.put("message",errorOperation);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        }
    }

}
