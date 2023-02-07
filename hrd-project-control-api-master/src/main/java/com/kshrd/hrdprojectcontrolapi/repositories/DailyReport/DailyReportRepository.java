package com.kshrd.hrdprojectcontrolapi.repositories.DailyReport;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterByCurrentDate;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.repositories.DailyReport.Provider.DailyReportProvider;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiFilterCurrentDateResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiGetByDay;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportInsertResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyReportRepository {

    /************************************************************
     * TODO: Insert DailyReport
     **********************************************************/

    @InsertProvider(value = DailyReportProvider.class, method = "insertDailyReport")
    Boolean inSert(DailyReportInsertResponse dailyReport);


    /************************************************************
     * TODO: filter daily report by current_date
     **********************************************************/

    @SelectProvider(type = DailyReportProvider.class, method = "filterbycurrentdate")
    @Results({
            @Result(property = "Id", column = "id"),
            @Result(property = "currentDate", column = "current_date"),
            @Result(property = "developerName", column = "developer_name"),
            @Result(property = "mainTask", column = "main_task"),
            @Result(property = "subTask", column = "sub_task"),
            @Result(property = "deadline", column = "deadline"),
            @Result(property = "progressPercentage", column = "progress_percentage"),
            @Result(property = "status", column = "status"),
            @Result(property = "message", column = "message"),

    })
    List<DailyReportApiFilterCurrentDateResponse> filterbycurrentdate(@Param("filter") DailyReportFilterByCurrentDate filter);



    /************************************************************
     * TODO: filter daily report by proeject_id
     **********************************************************/

    @SelectProvider(type = DailyReportProvider.class, method = "filterbyprojectid")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "currentDate", column = "current_date"),
            @Result(property = "developerName", column = "developer_name"),
            @Result(property = "mainTask", column = "main_task"),
            @Result(property = "subTask", column = "sub_task"),
            @Result(property = "deadLine", column = "deadline"),
            @Result(property = "progressPercentage", column = "progress_percentage"),
            @Result(property = "status", column = "status"),
    })
    List<DailyReportApiGetByDay> filterbyprojectid(@Param("filter") DailyReportFilterById filter);
}
