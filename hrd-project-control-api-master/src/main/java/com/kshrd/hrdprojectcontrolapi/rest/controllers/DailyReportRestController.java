package com.kshrd.hrdprojectcontrolapi.rest.controllers;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterByCurrentDate;
import com.kshrd.hrdprojectcontrolapi.models.hpc.DailyReportFilterById;
import com.kshrd.hrdprojectcontrolapi.rest.message.MessageProperties;
import com.kshrd.hrdprojectcontrolapi.rest.message.Resources;
import com.kshrd.hrdprojectcontrolapi.rest.request.DailyReportApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import com.kshrd.hrdprojectcontrolapi.services.dailyreport.DailyReportServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This rest controller is use for get all daily report and also filter by currentDate
 * and project id
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("${api.version}/daily-reports")
@Api(tags = "Daily Report", value = "Daily Report", description = "Controller for daily Report control")
public class DailyReportRestController {

    private DailyReportServiceImp dailyReportService;

    private MessageProperties message;

    @Autowired
    public void SetDailyReportController(DailyReportServiceImp dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @PostMapping()
    @ApiOperation(value = "Insert Daily Report", response = DailyReportApiRequest.class)
    public ResponseEntity<BaseApiResponse<DailyReportApiRequest>> insertDailyReport(@RequestBody DailyReportApiRequest dailyReportApiRequest) {

        //for show message
        String inserted=message.inserted("Daily report");
        String insertError=message.insertError("Daily report");
        String errorOperation=message.errorOperation("Daily report");

        BaseApiResponse<DailyReportApiRequest> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        DailyReportInsertResponse dailyReport = mapper.map(dailyReportApiRequest, DailyReportInsertResponse.class);


        try {
            Boolean result = dailyReportService.insertDailyReport(dailyReport);
            DailyReportApiRequest result2 = mapper.map(result, DailyReportApiRequest.class);
            if(result)
            {
                response.setMessage(inserted);
                response.setData(dailyReportApiRequest);
                response.setStatus(HttpStatus.CREATED);
            }
            else
            {
                response.setMessage(insertError);
                response.setData(dailyReportApiRequest);
                response.setData(result2);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * -Filter by (Current Date) show info detail one day that student did,
     * use class (DailyReportAPiFilterResponse)
     *      *       -view data by(DailyReportApiFilterCurrentDateResponse)
     *      *       - filter by class(DailyReportFilter)
     * @param dailyReportFilterbyCurrentDate
     * @return
     */
    @GetMapping()
    @ApiOperation("Show daily reports by filter current_date")
    public ResponseEntity<BaseApiResponse<List<DailyReportApiFilterCurrentDateResponse>>> FilterByCurrentDate(DailyReportFilterByCurrentDate dailyReportFilterbyCurrentDate) {

          String selected=message.selected("Daily reports");
          String dataEmpty=message.dataEmpty("Daily report");
          String errorOperation=message.errorOperation("Daily report");

        BaseApiResponse<List<DailyReportApiFilterCurrentDateResponse>> response = new BaseApiResponse<>();
        try {
            List<DailyReportApiFilterCurrentDateResponse> value = dailyReportService.filterbycurrentdate(dailyReportFilterbyCurrentDate);
            if(!value.isEmpty())
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    /**
     * -Filter by (Project_id) show info daily report all day that student did,
     * use class (DailyReportAPiFilterResponse)
     *      *       -view data by(DailyReportApiGetByDay)
     *      *       - filter by class(DailyReportFilterById)
     * @param dailyReportFilterById
     * @return
     */
    @GetMapping("/filter")
    @ApiOperation("Show all daily reports by filter project id")
    public ResponseEntity<BaseApiResponse<List<DailyReportApiGetByDay>>> FilterbyProjectID(DailyReportFilterById dailyReportFilterById) {

        String selected=message.selected("Daily reports");
        String dataEmpty=message.dataEmpty("Daily report");
        String errorOperation=message.errorOperation("Daily report");


        BaseApiResponse<List<DailyReportApiGetByDay>> response = new BaseApiResponse<>();
        try {
            List<DailyReportApiGetByDay> value = dailyReportService.filterbyprojectid(dailyReportFilterById);
            if(!value.isEmpty())
            {
                response.setMessage(selected);
                response.setData(value);
                response.setStatus(HttpStatus.OK);
            }
            else {
                response.setMessage(dataEmpty);
                response.setData(value);
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            response.setMessage(errorOperation);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



}
