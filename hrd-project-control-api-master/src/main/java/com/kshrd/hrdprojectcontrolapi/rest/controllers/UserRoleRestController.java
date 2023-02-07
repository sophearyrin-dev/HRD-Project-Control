package com.kshrd.hrdprojectcontrolapi.rest.controllers;

import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.rest.response.BaseApiResponse;
import com.kshrd.hrdprojectcontrolapi.services.role.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class rest controller user for get all user data
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "Role", value = "User", description = "Controller for Role User")
public class UserRoleRestController {
    RoleService roleService;
    @Autowired
    public UserRoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * This method use for get all user data
     * @return
     */
    @GetMapping()
    public ResponseEntity<BaseApiResponse<List<Role>>> findAll()
    {
        BaseApiResponse<List<Role>> response=new BaseApiResponse<>();
        try {
            List<Role> value=roleService.findAllRole();
            if(!value.isEmpty())
            {
                response.setMessage("FIND ALL USER ROLE.");
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage("NO DATA IN USER ROLE.");
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage("NO DATA IN USER ROLE.");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
