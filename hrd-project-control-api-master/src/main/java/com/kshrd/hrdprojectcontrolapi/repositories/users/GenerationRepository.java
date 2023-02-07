package com.kshrd.hrdprojectcontrolapi.repositories.users;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.rest.controllers.GenerationRestController;
import com.kshrd.hrdprojectcontrolapi.rest.request.GenerationApiRequest;
import org.apache.ibatis.annotations.*;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.stereotype.Repository;

import java.util.List;
/**********************************************************************
 * Description: Generation interface for insert,delete,update,get and findOne in data base
 **********************************************************************/
@Repository
public interface GenerationRepository {
    @Insert("INSERT INTO generation (name) VALUES (#{name})")
    @Results({
            @Result(property = "generationId",column = "id"),
            @Result(property = "name",column = "name")
    })
    Boolean insertGeneration(GenerationApiRequest generationApiRequest);

    @Update("UPDATE generation SET name=#{name} WHERE id=#{generationId}")
    Boolean updateGeneration(GenerationApiRequest generationApiRequest);

    @Update("UPDATE generation SET status=false WHERE id=#{generationId}")
    Boolean deleteGeneration(int id);

    @Select("SELECT * FROM generation WHERE status=true order by id DESC")
    @Results({
            @Result(property = "generationId",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "status",column = "status")
    })
    List<Generation> findAllGeneration();

}
