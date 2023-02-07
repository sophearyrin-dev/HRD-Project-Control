package com.kshrd.hrdprojectcontrolapi.repositories.users.provider;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserUpdateApiResquest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {


    public String insertUser()
    {
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("username,password,gender,role_id,university_id,generation_id","#{username},#{password},#{gender},#{roleId},#{universityId},#{generationId}");
        }}.toString();
    }

    public String updateUser(@Param("userUpdateApiResquest") UserUpdateApiResquest userUpdateApiResquest, int userId)
    {
        return new SQL(){{
            UPDATE("users set username=#{userUpdateApiResquest.username},password=#{userUpdateApiResquest.password},gender=#{userUpdateApiResquest.gender},role_id=#{userUpdateApiResquest.roleId},university_id=#{userUpdateApiResquest.universityId},generation_id=#{userUpdateApiResquest.generationId}");
            WHERE("id=#{userUpdateApiResquest.userId}");
        }}.toString();
    }


    public String userAction(@Param("userActionDelete") UserActionDeleteApiRequest userActionDelete, int userId)
    {
        return new SQL(){{
            UPDATE("users set status=false");
            WHERE("id=#{userActionDelete.id}");
        }}.toString();
    }
}

