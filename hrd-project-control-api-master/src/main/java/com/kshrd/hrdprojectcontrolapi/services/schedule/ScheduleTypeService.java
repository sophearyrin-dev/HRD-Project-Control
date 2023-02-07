package com.kshrd.hrdprojectcontrolapi.services.schedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilter;
import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleTypeApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiFilterResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiResponse;

import java.util.List;
public interface ScheduleTypeService {

    Boolean insertType(ScheduleTypeApiRequest scheduleTypeApiRequest);

    Boolean deleteType(int id);
    Boolean updateType(ScheduleTypeApiRequest scheduleTypeApiRequest);


    List<ScheduleTypeApiResponse> findAllType();

    List<ScheduleTypeApiFilterResponse> findAllWithFilter(ScheduleTypeFilter filter);
}
