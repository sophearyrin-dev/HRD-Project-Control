package com.kshrd.hrdprojectcontrolapi.services.documentschedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentScheduleApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentScheduleApiResponse;

import java.util.List;

public interface DocumentScheduleService {

    Boolean insertDocumentSchedule(DocumentSchedule documentSchedule);

    Boolean deleteDocumentSchedule(int id);

    Boolean updateDocumentSchedule(DocumentScheduleApiRequest documentScheduleApiRequest);

    Boolean updateStatusDocumentSchedule(ScheduleAction scheduleAction, int id);

    List<DocumentScheduleApiResponse> findAllDocumentSchedule();

    DocumentScheduleApiResponse findOneDocumentSchedule(int id);
}
