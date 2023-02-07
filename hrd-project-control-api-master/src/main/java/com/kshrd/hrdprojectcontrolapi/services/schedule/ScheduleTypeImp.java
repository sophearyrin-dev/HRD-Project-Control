package com.kshrd.hrdprojectcontrolapi.services.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilter;
import com.kshrd.hrdprojectcontrolapi.models.users.ScheduleType;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.ScheduleTypeRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleTypeApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiFilterResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ScheduleTypeApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTypeImp implements ScheduleTypeService {

    ScheduleTypeRepository scheduleTypeRepository;

    @Autowired
    public ScheduleTypeImp(ScheduleTypeRepository scheduleTypeRepository) {
        this.scheduleTypeRepository = scheduleTypeRepository;
    }

    @Override
    public Boolean insertType(ScheduleTypeApiRequest scheduleTypeApiRequest) {
        return scheduleTypeRepository.insertType(scheduleTypeApiRequest);
    }

    @Override
    public Boolean deleteType(int id) {
        return scheduleTypeRepository.deleteType(id);
    }

    @Override
    public Boolean updateType(ScheduleTypeApiRequest scheduleTypeApiRequest) {
        return scheduleTypeRepository.updateType(scheduleTypeApiRequest);
    }



    @Override
    public List<ScheduleTypeApiResponse> findAllType()
    {
        return scheduleTypeRepository.findAllType();
    }

    @Override
    public List<ScheduleTypeApiFilterResponse> findAllWithFilter(ScheduleTypeFilter filter)
    {
        return scheduleTypeRepository.findAllWithFilter(filter);
    }
}
