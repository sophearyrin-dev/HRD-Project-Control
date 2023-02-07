package com.kshrd.hrdprojectcontrolapi.repositories.schedule;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentTopic;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider.DocumentTopicProvider;
import com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider.ProjectTopicProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.ProjectTopicApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.response.DocumentTopicApiResponse;
import com.kshrd.hrdprojectcontrolapi.rest.response.ProjectTopicApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**********************************************************************
 * Description: Document topic interface for insert,delete,update,get and findOne in data base
 **********************************************************************/
@Repository
public interface DocumentTopicRepository {

    @InsertProvider(type = DocumentTopicProvider.class,method = "insertDocumentTopic")
    Boolean insertDocumentTopic(DocumentTopicApiRequest documentTopicApiRequest);

    @UpdateProvider(type = DocumentTopicProvider.class,method = "updateDocumentTopic")
    @Results({
            @Result(property = "projectScheduleId",column = "document_schedule_id"),
            @Result(property = "startDate",column = "start_date"),
            @Result(property = "endDate",column = "end_date"),
    })
    Boolean updateDocumentTopic(@Param("documentTopicApiRequest") DocumentTopicApiRequest documentTopicApiRequest);

    @DeleteProvider(type = DocumentTopicProvider.class,method = "deleteDocumentTopic")
    Boolean deleteDocumentTopic(int id);

    @Select("SELECT ds.id,ds.name,ds.start_date,ds.end_date,ds.status \n" +
            "FROM document_schedule_topic ds\n" +
            "INNER JOIN document_schedule sc on ds.document_schedule_id = sc.id\n" +
            "WHERE ds.document_schedule_id=#{id} AND sc.status=true AND ds.status=true ORDER BY ds.id DESC")
    @Results({
            @Result(property = "startDate",column = "start_date"),
            @Result(property = "endDate",column = "end_date")
    })
    List<DocumentTopicApiResponse> findAllDocumentTopic(int id);
}
