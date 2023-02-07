package com.kshrd.hrdprojectcontrolapi.repositories.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentScheduleApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentScheduleApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************
 * Description: Document Schedule interface for insert,delete,update,get and findOne in data base
 **********************************************************************/

@Repository
public interface DocumentScheduleRepository {

    @Insert("INSERT INTO document_schedule (schedule_type_id) VALUES (#{typeId})")
    @Options(useGeneratedKeys =true ,keyProperty = "id",keyColumn = "id")
    @Results({
            @Result(property = "typeId",column = "schedule_type_id"),
    })
    Boolean insertDocumentSchedule(DocumentSchedule documentSchedule);

    @Update("UPDATE document_schedule SET schedule_type_id=#{typeId} WHERE id=#{id}")
    Boolean updateDocumentSchedule(DocumentScheduleApiRequest documentScheduleApiRequest);

    @Update("UPDATE document_schedule SET status=#{scheduleAction.status} WHERE id=#{scheduleAction.id}")
    Boolean updateStatusDocumentSchedule(@Param("scheduleAction") ScheduleAction scheduleAction, int id);


    @Delete("DELETE FROM document_schedule WHERE id=#{id}")
    Boolean deleteDocumentSchedule(int id);

    /**
     * This mybatis use for select document schedule and join to schedule type,generation,course
     * @return
     */
    @Select("SELECT aa.id,aa.status,bb.id AS schedule_id,bb.name,bb.status AS schedule_status,b.id AS generation_id,b.name AS generation_name,b.status AS generation_status,c.id AS course_id,c.name AS course_name\n" +
            "FROM document_schedule AS aa \n" +
            "LEFT JOIN schedule_type AS bb ON aa.schedule_type_id=bb.id\n" +
            "LEFT JOIN generation AS b ON bb.generation_id=b.id \n" +
            "LEFT JOIN course AS c ON bb.course_id=c.id  ORDER BY aa.id DESC")
    @Results({
            @Result(property ="id",column ="id"),
            @Result(property ="type.name",column ="name"),
            @Result(property = "type.typeId",column = "schedule_id"),
            @Result(property = "type.status",column = "schedule_status"),
            @Result(property = "type.generation.generationId",column = "generation_id"),
            @Result(property = "type.generation.status",column = "generation_status"),
            @Result(property = "type.course.courseId",column = "course_id"),
            @Result(property = "type.generation.name",column = "generation_name"),
            @Result(property = "type.course.name",column = "course_name")
    })
    List<DocumentScheduleApiResponse> findAllDocumentSchedule();

    /**
     * find a document schedule
     * @param id
     * @return
     */

    @Select("SELECT aa.id,aa.status,bb.id AS schedule_id,bb.name,bb.status AS schedule_status,b.id AS generation_id,b.name AS generation_name,b.status AS generation_status,c.id AS course_id,c.name AS course_name\n" +
            "FROM document_schedule AS aa \n" +
            "LEFT JOIN schedule_type AS bb ON aa.schedule_type_id=bb.id\n" +
            "LEFT JOIN generation AS b ON bb.generation_id=b.id \n" +
            "LEFT JOIN course AS c ON bb.course_id=c.id WHERE aa.id=#{id} ORDER BY aa.id DESC")
    @Results({
            @Result(property ="id",column ="id"),
            @Result(property ="type.name",column ="name"),
            @Result(property = "type.typeId",column = "schedule_id"),
            @Result(property = "type.status",column = "schedule_status"),
            @Result(property = "type.generation.generationId",column = "generation_id"),
            @Result(property = "type.generation.status",column = "generation_status"),
            @Result(property = "type.course.courseId",column = "course_id"),
            @Result(property = "type.generation.name",column = "generation_name"),
            @Result(property = "type.course.name",column = "course_name")
    })
    DocumentScheduleApiResponse findOneDocumentSchedule(int id);
}
