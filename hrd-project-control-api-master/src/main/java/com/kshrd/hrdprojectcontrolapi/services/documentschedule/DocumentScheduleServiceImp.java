package com.kshrd.hrdprojectcontrolapi.services.documentschedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentSchedule;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectSchedule;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.DocumentScheduleRepository;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentScheduleApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ScheduleAction;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentScheduleApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentScheduleServiceImp implements DocumentScheduleService {
    DocumentScheduleRepository documentScheduleRepository;
    @Autowired
    public DocumentScheduleServiceImp(DocumentScheduleRepository documentScheduleRepository) {
        this.documentScheduleRepository = documentScheduleRepository;
    }


    @Override
    public Boolean insertDocumentSchedule(DocumentSchedule documentSchedule) {
        return documentScheduleRepository.insertDocumentSchedule(documentSchedule);
    }

    @Override
    public Boolean deleteDocumentSchedule(int id) {
        return documentScheduleRepository.deleteDocumentSchedule(id);
    }

    @Override
    public Boolean updateDocumentSchedule(DocumentScheduleApiRequest documentScheduleApiRequest) {
        return documentScheduleRepository.updateDocumentSchedule(documentScheduleApiRequest);
    }

    @Override
    public Boolean updateStatusDocumentSchedule(ScheduleAction scheduleAction, int id) {
        return documentScheduleRepository.updateStatusDocumentSchedule(scheduleAction, id);
    }

    @Override
    public List<DocumentScheduleApiResponse> findAllDocumentSchedule() {
        return documentScheduleRepository.findAllDocumentSchedule();
    }

    @Override
    public DocumentScheduleApiResponse findOneDocumentSchedule(int id) {
        return documentScheduleRepository.findOneDocumentSchedule(id);
    }
}
