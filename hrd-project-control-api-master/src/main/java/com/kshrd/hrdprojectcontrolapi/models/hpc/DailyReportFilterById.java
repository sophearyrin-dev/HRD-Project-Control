/*************************************************************
 * Author: Sot Sirymony
 * Create Date: july,28,2020
 * Developer Group: HRD PROJECT CONTROL
 * Description: using filter
 *************************************************************/
package com.kshrd.hrdprojectcontrolapi.models.hpc;

public class DailyReportFilterById {

    Integer id;

    public DailyReportFilterById(){};

    public DailyReportFilterById(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return "DailyReportFilterById{" +
                "id=" + id +
                '}';
    }
}
