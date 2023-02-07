package com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision;

import org.apache.ibatis.jdbc.SQL;

public class ProjectDivisionProvider {

    public String insertUserDivision()
    {
        return new SQL(){{
            INSERT_INTO("project_division");
            VALUES("project_id, user_id, project_schedule_id, document_schedule_id, schedule_type_id","#{projectId},#{userId},#{documentId},#{scheduleId},#{typeId}");
        }}.toString();
    }

    public String findAllProjectMainTask() {
        return new SQL(){{
            SELECT("pst.name AS main_task_name, pst.start_date, pst.end_date");
            FROM("schedule_type st INNER JOIN project_schedule ps ON st.id = ps.schedule_type_id INNER JOIN project_schedule_topic pst ON pst.project_schedule_id = ps.id");
            WHERE("pst.percentage NOTNULL AND st.id = #{scheduleTypeId}");
        }}.toString();
    }

    public String findAllIdOfParentTaskByProjectId() {
        return new SQL(){{
            SELECT("id AS parent_task_id");
            FROM("task");
            WHERE("project_id = #{projectId} AND parent_task_id ISNULL AND name != 'Document'");
        }}.toString();
    }

    public String insertDefaultSubTaskToEachParentByProjectId() {
        return new SQL(){{
            INSERT_INTO("task");
            VALUES("name, percentage, parent_task_id, project_id", "#{defaultSubTaskName},#{percentage},#{parentTaskId},#{projectId}");
        }}.toString();
    }

    public String findAllDocumentTask() {
        return new SQL(){{
            SELECT("dst.name AS document_task_name, dst.start_date, dst.end_date");
            FROM("schedule_type st INNER JOIN document_schedule ds ON st.id = ds.schedule_type_id INNER JOIN document_schedule_topic dst ON dst.document_schedule_id = ds.id");
            WHERE("st.id = #{scheduleTypeId}");
        }}.toString();
    }

    public String findDocumentTaskDeadlineByScheduleTypeId() {
        return new SQL(){{
            SELECT("MIN(dst.start_date) as start_date, MAX(dst.end_date) as end_date");
            FROM("schedule_type st INNER JOIN document_schedule ds ON st.id = ds.schedule_type_id INNER JOIN document_schedule_topic dst ON dst.document_schedule_id = ds.id");
            WHERE("st.id = #{scheduleTypeId}");
        }}.toString();
    }

    public String findIdOfDocumentTaskByProjectId() {
        return new SQL(){{
            SELECT("id");
            FROM("task");
            WHERE("project_id = #{projectId} AND name = 'Document'");
        }}.toString();
    }

    public String insertMainTask() {
        return new SQL(){{
            INSERT_INTO("task");
            VALUES("name, start_date, end_date, project_id", "#{taskName},#{startDate},#{endDate},#{projectId}");
        }}.toString();
    }

    public String insertDocumentSubTask() {
        return new SQL(){{
            INSERT_INTO("task");
            VALUES("name, percentage, start_date, end_date, parent_task_id, project_id", "#{taskName},#{percentage},#{startDate},#{endDate},#{parentTaskId},#{projectId}");
        }}.toString();
    }

    /**
     *
     * @return
     */
    public String deleteProjectDivision() {
        return new SQL(){{
            DELETE_FROM("project_division");
            WHERE("project_division.project_division_id=#{project_division_id}");
        }}.toString();
    }
}
