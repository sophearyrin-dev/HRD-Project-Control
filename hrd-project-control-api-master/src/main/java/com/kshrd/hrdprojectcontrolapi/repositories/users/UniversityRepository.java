package com.kshrd.hrdprojectcontrolapi.repositories.users;

import com.kshrd.hrdprojectcontrolapi.models.users.University;
import com.kshrd.hrdprojectcontrolapi.rest.request.UniversityApiRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************
 * Description: University interface for insert,delete,update,get and findOne in data base
 **********************************************************************/

@Repository
public interface UniversityRepository {

    @Insert("INSERT INTO university (name) VALUES (#{name})")
    @Results({
            @Result(property = "universityId",column = "id"),
            @Result(property = "name",column = "name")
    })
    Boolean insert(UniversityApiRequest universityApiRequest);


    @Update("UPDATE university SET name=#{name} WHERE id=#{universityId}")
    Boolean update(UniversityApiRequest universityApiRequest);


    @Update("UPDATE university SET status=false WHERE id=#{universityId}")
    Boolean delete(int id);


    @Select("SELECT * FROM university WHERE status=true order by id DESC")
    @Results({
            @Result(property = "universityId",column = "id"),
            @Result(property = "name",column = "name")
    })
    List<University> findAll();
}
