package com.kshrd.hrdprojectcontrolapi.services.role;

import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.repositories.users.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    UserRoleRepository userRoleRepository;
    @Autowired
    public RoleServiceImp(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<Role> findAllRole() {
        return userRoleRepository.findAllRole();
    }
}
