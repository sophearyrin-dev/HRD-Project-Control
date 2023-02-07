package com.kshrd.hrdprojectcontrolapi.repositories.DailyReport.Provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterByCurrentDate;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectFilter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class DailyReportProvider {

    public String insertDailyReport(){
        return new SQL(){{
            INSERT_INTO("daily_report");
            VALUES("developer_name,\"current_date\",main_task,sub_task,deadline,progress_percentage,message,project_id","#{developer_name},#{current_date},#{main_task},#{sub_task},#{deadline},#{progress_percentage},#{message},#{project_id}");
        }}.toString();
    }

    public String filterbycurrentdate(@Param("filter") DailyReportFilterByCurrentDate dailyReportFilter){
        return new SQL(){{
            SELECT(" b.* from daily_report b");

            if(dailyReportFilter.getCurrent_date()!=null && dailyReportFilter.getProject_id()!=null)
                WHERE("b.current_date=#{filter.current_date} and b.project_id=#{filter.project_id}");
        }}.toString();
    }

    public String filterbyprojectid(@Param("filter") DailyReportFilterById dailyReportFilter){
        return new SQL(){{
            if(dailyReportFilter.getId()!=null )
            SELECT ("distinct  (select developer_name from daily_report where project_id =#{filter.id} limit 1),\"current_date\"::date,(select main_task from daily_report where project_id =#{filter.id} limit 1),(select sub_task from daily_report where project_id =#{filter.id} limit 1),(select deadline from daily_report where project_id =#{filter.id} limit 1),(select progress_percentage from daily_report where project_id =#{filter.id} limit 1),(select status from daily_report where project_id =#{filter.id} limit 1),(select message from daily_report where project_id =#{filter.id} limit 1)");
            FROM ("daily_report b"  );
            INNER_JOIN("project c  on b.project_id= c.id");
            if(dailyReportFilter.getId()!=null )
                WHERE("b.project_id=#{filter.id}");
                ORDER_BY("\"current_date\" DESC");
        }}.toString();
    }
}