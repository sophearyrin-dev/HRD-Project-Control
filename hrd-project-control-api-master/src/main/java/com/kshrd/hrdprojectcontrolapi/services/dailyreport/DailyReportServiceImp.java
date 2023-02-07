package com.kshrd.hrdprojectcontrolapi.services.dailyreport;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterByCurrentDate;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.repositories.DailyReport.DailyReportRepository;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiFilterCurrentDateResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportApiGetByDay;
import com.kshrd.hrdprojectcontrolapi.rest.response.DailyReportInsertResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyReportServiceImp implements DailyReportService {

    private DailyReportRepository dailyReportRepository;

    @Autowired
    public void SetDailyReportServiceImp(DailyReportRepository dailyReportRepository){

        this.dailyReportRepository=dailyReportRepository;
    }


    @Override
    public Boolean insertDailyReport(DailyReportInsertResponse dailyReport) {

        return dailyReportRepository.inSert(dailyReport);
    }


    @Override
    public List<DailyReportApiFilterCurrentDateResponse> filterbycurrentdate(DailyReportFilterByCurrentDate filter) {

        return dailyReportRepository.filterbycurrentdate(filter);
    }

    public List<DailyReportApiGetByDay> filterbyprojectid(DailyReportFilterById filter){
        return dailyReportRepository.filterbyprojectid(filter);
    }

}
