package com.kshrd.hrdprojectcontrolapi.services.users;

import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.User;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserUpdateApiResquest;
import com.kshrd.hrdprojectcontrolapi.rest.response.UserApiResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    Boolean updateUser(UserUpdateApiResquest userUpdateApiResquest, int userId);

    List<UserApiResponse> findAllUsers(int id);

    Boolean insertUser(UserApiRequest userApiRequest);

    List<Role> findRolesByUserId(int id);

    User findByUsername(String username);

    Boolean userActionDelete(UserActionDeleteApiRequest userActionDelete, int userId);

    UserApiResponse findOne(int id);
}
