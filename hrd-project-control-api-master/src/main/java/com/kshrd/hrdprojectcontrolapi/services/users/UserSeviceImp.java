package com.kshrd.hrdprojectcontrolapi.services.users;

import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.User;
import com.kshrd.hrdprojectcontrolapi.repositories.users.UserRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserUpdateApiResquest;
import com.kshrd.hrdprojectcontrolapi.rest.response.UserApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserSeviceImp implements UserService{

    UserRepository userRepository;
    @Autowired
    public UserSeviceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean insertUser(UserApiRequest userApiRequest) {
        return userRepository.insertUser(userApiRequest);
    }

    @Override
    public List<Role> findRolesByUserId(int id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean userActionDelete(UserActionDeleteApiRequest userActionDelete, int userId) {
        return userRepository.actionUser(userActionDelete, userId);
    }

    @Override
    public UserApiResponse findOne(int id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public Boolean updateUser(UserUpdateApiResquest userUpdateApiResquest, int userId) {
        return userRepository.updateUser(userUpdateApiResquest, userId);
    }

    @Override
    public List<UserApiResponse> findAllUsers(int generationId) {
       if(generationId==0){
           return this.userRepository.findAllUser();
       }
       else {
           return this.userRepository.findAllUserByGenerationId(generationId);
       }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return user;
    }
}
