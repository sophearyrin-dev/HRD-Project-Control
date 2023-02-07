package com.kshrd.hrdprojectcontrolapi.repositories.schedule.provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.DocumentTopic;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectTopic;
import com.kshrd.hrdprojectcontrolapi.rest.request.DocumentTopicApiRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class DocumentTopicProvider {
    public String insertDocumentTopic()
    {
        return new SQL(){{
            INSERT_INTO("document_schedule_topic");
            VALUES("name,start_date,end_date,document_schedule_id","#{name},#{startDate},#{endDate},#{projectScheduleId}");
        }}.toString();
    }

    public String deleteDocumentTopic(int id)
    {
        return new SQL(){{
            UPDATE("document_schedule_topic SET status=false");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String updateDocumentTopic(@Param("documentTopicApiRequest") DocumentTopicApiRequest documentTopicApiRequest)
    {
        return new SQL(){{
            UPDATE("document_schedule_topic set name=#{documentTopicApiRequest.name},start_date=#{documentTopicApiRequest.startDate},end_date=#{documentTopicApiRequest.endDate}");
            WHERE("id=#{documentTopicApiRequest.id}");
        }}.toString();
    }
}
