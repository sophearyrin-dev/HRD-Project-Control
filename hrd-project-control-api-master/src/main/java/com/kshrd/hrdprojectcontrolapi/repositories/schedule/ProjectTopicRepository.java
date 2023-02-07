package com.kshrd.hrdprojectcontrolapi.repositories.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider.ProjectTopicProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectTopicApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**********************************************************************
 * Description: Project topic interface for insert,delete,update,get and findOne in data base
 **********************************************************************/
@Repository
public interface ProjectTopicRepository {
    @InsertProvider(type = ProjectTopicProvider.class,method = "insertProjectTopic")
    Boolean insertProjectTopic(ProjectTopicApiRequest projectTopicApiRequest);
    @UpdateProvider(type = ProjectTopicProvider.class,method = "updateProjectTopic")
    @Results({
            @Result(property = "startDate",column = "start_date"),
            @Result(property = "endDate",column = "end_date"),
    })
    Boolean updateProjectTopic(@Param("projectTopicApiRequest") ProjectTopicApiRequest projectTopicApiRequest);

    @DeleteProvider(type = ProjectTopicProvider.class,method = "deleteProjectTopic")
    Boolean deleteProjectTopic(int id);

    //get all project schedule topic by project schedule id and project schedule status true
    @Select("SELECT pst.id,pst.name,pst.status,pst.start_date,pst.end_date,pst.percentage \n" +
            "FROM project_schedule_topic pst\n" +
            "INNER JOIN project_schedule ps on pst.project_schedule_id = ps.id\n" +
            "WHERE pst.project_schedule_id=#{id} AND ps.status=true AND pst.status=true ORDER BY pst.id DESC")
    @Results({
            @Result(property = "startDate",column = "start_date"),
            @Result(property = "endDate",column = "end_date")
    })
    List<ProjectTopicApiResponse> findAllProjectTopic(int id);
}
