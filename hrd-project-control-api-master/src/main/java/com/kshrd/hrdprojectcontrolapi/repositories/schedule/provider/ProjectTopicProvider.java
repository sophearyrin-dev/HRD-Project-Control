package com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ProjectTopicProvider {
    public String insertProjectTopic()
    {
        return new SQL(){{
            INSERT_INTO("project_schedule_topic");
            VALUES("name,start_date,end_date,percentage,project_schedule_id","#{name},#{startDate},#{endDate},#{percentage},#{projectScheduleId}");
        }}.toString();
    }
    public String deleteProjectTopic(int id)
    {
        return new SQL(){{
            UPDATE("project_schedule_topic SET status=false");
            WHERE("id=#{id}");
        }}.toString();
    }
    public String updateProjectTopic(@Param("projectTopicApiRequest") ProjectTopicApiRequest projectTopicApiRequest)
    {
        return new SQL(){{
            UPDATE("project_schedule_topic set name=#{projectTopicApiRequest.name},start_date=#{projectTopicApiRequest.startDate},end_date=#{projectTopicApiRequest.endDate},percentage=#{projectTopicApiRequest.percentage}");
            WHERE("id=#{projectTopicApiRequest.id}");
        }}.toString();
    }
}
