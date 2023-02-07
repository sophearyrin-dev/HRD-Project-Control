package com.kshrd.hrdprojectcontrolapi.services.dailyreport;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterByCurrentDate;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiFilterCurrentDateResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiGetByDay;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportInsertResponse;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public interface DailyReportService {

        Boolean insertDailyReport(DailyReportInsertResponse dailyReport);

        List<DailyReportApiFilterCurrentDateResponse> filterbycurrentdate(DailyReportFilterByCurrentDate filter);

        List<DailyReportApiGetByDay> filterbyprojectid(DailyReportFilterById filter);
    }
