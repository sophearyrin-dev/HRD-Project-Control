package com.kshrd.hrdprojectcontrolapi.repositories.project;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectUpdateApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ProjectProvider {

    public String insertProject()
    {
        return new SQL(){{
            INSERT_INTO("project");
            VALUES("name,objective,feature,generation_id,course_id","#{name},#{objective},#{feature},#{generationId},#{courseId}");
        }}.toString();
    }

    //update project by project id
    public String updateProject(@Param("projectUpdateApiRequest") ProjectUpdateApiRequest projectUpdateApiRequest)
    {
        return new SQL(){{
            UPDATE("project set name=#{projectUpdateApiRequest.name},objective=#{projectUpdateApiRequest.objective},feature=#{projectUpdateApiRequest.feature},generation_id=#{projectUpdateApiRequest.generationId},course_id=#{projectUpdateApiRequest.courseId}");
            WHERE("id=#{projectUpdateApiRequest.projectId}");
        }}.toString();
    }


    public String findAllWithFilter(@Param("filter") ProjectFilter projectFilter){
        return new SQL(){{
            SELECT(" b.*,g.name as generation_name,c.name as course_name from project b");
            INNER_JOIN("generation g on g.id=b.generation_id");
            INNER_JOIN("course c on c.id=b.course_id");

            if(projectFilter.getCourse_id()!=null && projectFilter.getGeneration_id()!=null)
                WHERE("b.generation_id=#{filter.generation_id} and b.course_id=#{filter.course_id} and b.status=true");
        }}.toString();
    }

}
