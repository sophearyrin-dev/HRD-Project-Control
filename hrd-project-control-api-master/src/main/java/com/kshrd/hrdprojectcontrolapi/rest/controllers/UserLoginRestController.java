package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.configurations.JwtConfigurations.JwtTokenUtil;
import com.kshrd.hrdprojectcontrolapi.models.users.User;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Validation;
import com.kshrd.hrdprojectcontrolapi.rest.request.LoginApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.JwtLoginApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.users.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * This class use for user login token
 */

@CrossOrigin
@RestController
@Api(tags = "User login", value = "Login", description = "Controller for User login")
public class UserLoginRestController {

    private Validation validation;

    private MessageProperties message;

    private AuthenticationManager authenticationManager;

    UserService userService;

    JwtTokenUtil jwtTokenUtil;

    BCryptPasswordEncoder encoder;

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public UserLoginRestController(UserService userService) {
        this.userService = userService;
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
     * This method use for post username and password
     * @param user
     * @return
     * @throws Exception
     */

    @PostMapping("${api.version}/users/authenticate")
    @ApiOperation(value = "User login")
    public ResponseEntity<BaseApiResponse<HashMap<String,Object>>> createAuthenticationToken(@RequestBody LoginApiRequest user)throws Exception {

        BaseApiResponse<HashMap<String,Object>> response=new BaseApiResponse<>();

        try{
            //check user
            boolean username=validation.checkInputString(user.getUsername());
            boolean password=validation.checkInputPassword(user.getPassword());

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

                response.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok(response);
            }
            UsernamePasswordAuthenticationToken value= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            authenticationManager.authenticate(value);


        }
        catch (BadCredentialsException e){
            throw new Exception("INCORRECT USERNAME AND PASSWORD",e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        User users =userService.findByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        JwtLoginApiResponse value=new JwtLoginApiResponse(token);

        HashMap<String,Object> data=new HashMap<>();
        data.put("userId",users.getUserId());
        data.put("name",userDetails.getUsername());

        data.put("role",userDetails.getAuthorities());

        data.put("data",value);

        if(!value.equals(""))
        {

            response.setMessage("Login is successfully");
            response.setData(data);
            response.setStatus(HttpStatus.OK);
        }
        else {
            response.setMessage("Login is invalid");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
