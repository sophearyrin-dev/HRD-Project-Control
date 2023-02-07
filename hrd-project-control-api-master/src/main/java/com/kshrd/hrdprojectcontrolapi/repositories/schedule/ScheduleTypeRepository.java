package com.kshrd.hrdprojectcontrolapi.repositories.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilter;
import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider.ProjectScheduleTypeProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleTypeApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiFilterResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************
 * Description: Schedule type interface for insert,delete,update,get and findOne in data base
 **********************************************************************/

@Repository
public interface ScheduleTypeRepository {

    @Insert("INSERT INTO schedule_type (name,generation_id,course_id) VALUES (#{name},#{generationId},#{courseId})")
    @Results({
            @Result(property = "typeId",column = "id"),
            @Result(property = "generationId",column = "generation_id"),
            @Result(property = "courseId",column = "course_id")
    })
    Boolean insertType(ScheduleTypeApiRequest scheduleTypeApiRequest);

    @Update("UPDATE schedule_type SET name=#{name}, generation_id=#{generationId}, course_id=#{courseId} WHERE id=#{typeId}")
    Boolean updateType(ScheduleTypeApiRequest scheduleTypeApiRequest);

    @Update("UPDATE schedule_type SET status=false WHERE id=#{typeId}")
    Boolean deleteType(int id);

    /**
     * This mybatis use for select data form schedule type and join to generation and course
     * @return
     */

    @Select("SELECT a.id,a.name,a.status,b.id AS generation_id,b.name AS generation_name,c.id AS course_id,b.status AS generation_status,c.name AS course_name FROM schedule_type AS a LEFT JOIN generation AS b ON a.generation_id=b.id LEFT JOIN\n" +
            "course AS c ON a.course_id=c.id WHERE a.status=true ORDER BY a.id DESC")
    @Results({
            @Result(property = "typeId",column = "id"),
            @Result(property = "status",column = "status"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "course.courseId",column = "course_id"),
            @Result(property = "generation.name",column = "generation_name"),
            @Result(property = "generation.status",column = "generation_status"),
            @Result(property = "course.name",column = "course_name"),

    })
    List<ScheduleTypeApiResponse> findAllType();

    /************************************************************
     * TODO: -get schedule types by filter generation_id and course_id

     **********************************************************/

    @SelectProvider(type = ProjectScheduleTypeProvider.class, method = "findAllWithFilter")
    List<ScheduleTypeApiFilterResponse> findAllWithFilter(@Param("filter") ScheduleTypeFilter filter);

}
