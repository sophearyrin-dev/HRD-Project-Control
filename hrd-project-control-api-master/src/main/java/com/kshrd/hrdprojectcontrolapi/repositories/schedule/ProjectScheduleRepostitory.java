package com.kshrd.hrdprojectcontrolapi.repositories.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentScheduleApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectScheduleApiRequst;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectScheduleApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**********************************************************************
 * Description: Project schedule interface for insert,delete,update,get and findOne in data base
 **********************************************************************/
@Repository
public interface ProjectScheduleRepostitory {

    @Insert("INSERT INTO project_schedule (schedule_type_id,group_meeting,status) VALUES (#{typeId},#{groupMeeting},#{status})")
    @Options(useGeneratedKeys =true ,keyProperty = "id",keyColumn = "id")
    @Results({
            @Result(property = "typeId",column = "schedule_type_id"),
            @Result(property = "groupMeeting",column = "group_meeting")
    })
    Boolean insertProjectSchedule(ProjectSchedule projectSchedule);

    @Update("UPDATE project_schedule SET schedule_type_id=#{typeId},group_meeting=#{groupMeeting} WHERE id=#{id}")
    Boolean updateProjectSchedule(ProjectScheduleApiRequst projectScheduleApiRequst);

    @Update("UPDATE project_schedule SET status=#{scheduleAction.status} WHERE id=#{scheduleAction.id}")
    Boolean updateStatusProjectSchedule(@Param("scheduleAction") ScheduleAction scheduleAction, int id);

    @Delete("DELETE FROM project_schedule WHERE id=#{id}")
    Boolean deleteProjectSchedule(int id);
    /**
     * This mybatis use for select project schedule and join to schedule type,generation,course
     * @return
     */
    @Select("SELECT aa.id,aa.group_meeting,aa.status AS schedule_status,bb.id AS type_id,bb.name,bb.status AS type_status,b.id AS generation_id,b.name AS generation_name,b.status AS generation_status,c.id AS course_id,c.name AS course_name\n" +
            "FROM project_schedule AS aa \n" +
            "LEFT JOIN schedule_type AS bb ON aa.schedule_type_id=bb.id\n" +
            "LEFT JOIN generation AS b ON bb.generation_id=b.id \n" +
            "LEFT JOIN course AS c ON bb.course_id=c.id  ORDER BY aa.id DESC ")
    @Results({
            @Result(property ="id",column ="id"),
            @Result(property = "status",column = "schedule_status"),
            @Result(property ="type.name",column ="name"),
            @Result(property ="groupMeeting",column ="group_meeting"),
            @Result(property = "type.typeId",column = "type_id"),
            @Result(property = "type.status",column = "type_status"),
            @Result(property = "type.generation.generationId",column = "generation_id"),
            @Result(property = "type.course.courseId",column = "course_id"),
            @Result(property = "type.generation.name",column = "generation_name"),
            @Result(property = "type.generation.status",column = "generation_status"),
            @Result(property = "type.course.name",column = "course_name")
    })
    List<ProjectScheduleApiResponse> findAllProjectSchedule();

    /**
     * find one project schedule
     * @return
     */

    @Select("SELECT aa.id,aa.group_meeting,aa.status AS schedule_status,bb.id AS type_id,bb.name,bb.status AS type_status,b.id AS generation_id,b.name AS generation_name,b.status AS generation_status,c.id AS course_id,c.name AS course_name\n" +
            "FROM project_schedule AS aa \n" +
            "LEFT JOIN schedule_type AS bb ON aa.schedule_type_id=bb.id\n" +
            "LEFT JOIN generation AS b ON bb.generation_id=b.id \n" +
            "LEFT JOIN course AS c ON bb.course_id=c.id WHERE aa.id=#{id} ORDER BY aa.id DESC ")
    @Results({
            @Result(property ="id",column ="id"),
            @Result(property = "status",column = "schedule_status"),
            @Result(property ="type.name",column ="name"),
            @Result(property ="groupMeeting",column ="group_meeting"),
            @Result(property = "type.typeId",column = "type_id"),
            @Result(property = "type.status",column = "type_status"),
            @Result(property = "type.generation.generationId",column = "generation_id"),
            @Result(property = "type.course.courseId",column = "course_id"),
            @Result(property = "type.generation.name",column = "generation_name"),
            @Result(property = "type.generation.status",column = "generation_status"),
            @Result(property = "type.course.name",column = "course_name")
    })
    ProjectScheduleApiResponse findOneProjectSchedule(int id);
}
