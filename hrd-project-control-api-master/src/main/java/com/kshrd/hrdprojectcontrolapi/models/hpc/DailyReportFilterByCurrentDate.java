package com.kshrd.hrdprojectcontrolapi.models.hpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyReportFilterByCurrentDate {

    String current_date;
    Integer project_id;

    public DailyReportFilterByCurrentDate(){};

    public DailyReportFilterByCurrentDate(String current_date, Integer project_id) {
        this.current_date = current_date;
        this.project_id = project_id;
    }

    public Date getCurrent_date()
    {
        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(current_date);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public void setCurrent_date(String current_date) {

        this.current_date = current_date;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }


}
