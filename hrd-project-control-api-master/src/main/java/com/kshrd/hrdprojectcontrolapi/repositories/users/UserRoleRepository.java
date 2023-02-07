package com.kshrd.hrdprojectcontrolapi.repositories.users;

import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.University;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/**********************************************************************
 * Description: Role interface for get in data base
 **********************************************************************/
@Repository
public interface UserRoleRepository {
    @Select("select * from role")
    @Results({
            @Result(property = "roleId",column = "id"),
            @Result(property = "name",column = "name")
    })
    List<Role> findAllRole();
}
