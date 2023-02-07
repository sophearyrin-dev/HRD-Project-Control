package com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ProjectScheduleTypeProvider {
    public String findAllWithFilter(@Param("filter") ScheduleTypeFilter projectScheduleTypeFilter){
        return new SQL(){{
            SELECT(" b.* from schedule_type b");

            if(projectScheduleTypeFilter.getCourse_id()!=null && projectScheduleTypeFilter.getGeneration_id()!=null)
                WHERE("b.generation_id=#{filter.generation_id} and b.course_id=#{filter.course_id}");
        }}.toString();
    }
}
