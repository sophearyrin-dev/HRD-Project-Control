package com.kshrd.hrdprojectcontrolapi.repositories.project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.repositories.users.provider.UserProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectUpdateApiRequest;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository {

    //insert project
    @InsertProvider(type = ProjectProvider.class,method = "insertProject")
    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "course.courseId",column = "course_id")
    })
    Boolean insertProject(ProjectApiRequest projectApiRequest);

    //Get all projects
    @Select( "select a.id, a.name, a.objective, a.feature, b.id as generation_id, b.name as generation_name, \n" +
            "c.id as course_id, c.name as course_name from project a \n" +
            "left JOIN generation b on a.generation_id=b.id \n" +
            "left join course c on a.course_id=c.id \n" +
            "where a.status = true\n" +
            "order by a.id DESC")
    @Results({
            @Result(property = "projectId", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "generation.name",column = "generation_name"),
            @Result(property = "course.courseId",column = "course_id"),
            @Result(property = "course.name",column = "course_name")
    })
    List<Project> findAllProject();

    //update project by project id
    @UpdateProvider(type = ProjectProvider.class,method = "updateProject")
    @Results({
            @Result(property = "projectId",column = "id"),
            @Result(property = "generationId",column = "generation_id"),
            @Result(property = "course.courseId",column = "course_id")
    })
    Boolean updateProject(@Param("projectUpdateApiRequest") ProjectUpdateApiRequest projectUpdateApiRequest);

    //delete project by project status
    @Update("UPDATE project SET status=false WHERE id=#{projectId}")
    Boolean deleteProject(int id);

    //=======> Mony Code
    /************************************************************
     * TODO: filter all project name by generation_id and course_id
     **********************************************************/

    @SelectProvider(type = ProjectProvider.class, method = "findAllWithFilter")
    @Results({
            @Result(property = "generation",column = "generation_name"),
            @Result(property = "course",column = "course_name")
    })
    List<ProjectApiFilterResponse> findAllWithFilter(@Param("filter") ProjectFilter filter);


    /**
     * Get project info by project id
     * @param id
     * @return
     */
    @Select( "select a.id, a.name, a.objective, a.feature, b.id as generation_id, b.name as generation_name, \n" +
            "c.id as course_id, c.name as course_name from project a \n" +
            "left JOIN generation b on a.generation_id=b.id \n" +
            "left join course c on a.course_id=c.id \n" +
            "where a.status = true and a.id=#{projectId}\n")
    @Results({
            @Result(property = "projectId", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "generation.name",column = "generation_name"),
            @Result(property = "course.courseId",column = "course_id"),
            @Result(property = "course.name",column = "course_name")
    })
    Project findOne(int id);
}
