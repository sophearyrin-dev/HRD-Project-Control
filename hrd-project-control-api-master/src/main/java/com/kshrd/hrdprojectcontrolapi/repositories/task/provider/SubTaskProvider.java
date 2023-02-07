package com.kshrd.hrdprojectcontrolapi.repositories.task.provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.RequestParam;

public class SubTaskProvider {

    public String findAllSubTaskByProjectId(@Param("filter") SubTaskProjectFilter subTaskProjectFilter) {
        return new SQL(){{
            SELECT("id, sub_task, handler_id, username, start_date, end_date, percentage, priority, issue, message, parent_task_name");
            FROM("sub_task_view");

            if(subTaskProjectFilter.getProjectId()!=null) {
                WHERE("project_id = #{filter.projectId}");
                ORDER_BY("handler_id");
            }
        }}.toString();
    }

    public String findAllSubTaskByParentTaskIdInProject(@Param("filter") SubTaskFilterByParentTask subTaskFilterByParentTask) {
        return new SQL(){{
            SELECT("id, sub_task, handler_id, username, start_date, end_date, percentage, priority, issue, message, parent_task_name");
            FROM("sub_task_view");

            if(subTaskFilterByParentTask.getParentTaskId() != null) {
                WHERE("parent_task_id = #{filter.parentTaskId}");
                ORDER_BY("handler_id");
            }
        }}.toString();
    }

    public String findAllSubTaskInProjectByUserId(@Param("filter") SubTaskUserFilter subTaskUserFilter) {
        return new SQL(){{
            SELECT("id, sub_task, handler_id, username, start_date, end_date, percentage, priority, issue, message, parent_task_name");
            FROM("sub_task_view");

            if(subTaskUserFilter.getProjectId()!=null && subTaskUserFilter.getHandlerId()!=null) {
                WHERE("project_id = #{filter.projectId} AND handler_id = #{filter.handlerId}");
                ORDER_BY("handler_id");
            }
        }}.toString();
    }

    public String updateSubTask() {
        return new SQL(){{
            UPDATE("task SET name=#{subTaskUpdateApiRequest.name},start_date=#{subTaskUpdateApiRequest.startDate},end_date=#{subTaskUpdateApiRequest.endDate},percentage=#{subTaskUpdateApiRequest.percentage},handler_id=#{subTaskUpdateApiRequest.handlerId},priority=#{subTaskUpdateApiRequest.priority}");
            WHERE("id=#{subTaskId}");
        }}.toString();
    }

    public String updateSubTaskByMember() {
        return new SQL(){{
            UPDATE("task SET percentage=#{subTaskUpdateApiRequestForTeamMember.percentage},issue=#{subTaskUpdateApiRequestForTeamMember.issue}, message=#{subTaskUpdateApiRequestForTeamMember.message}");
            WHERE("id = #{subTaskId}");
        }}.toString();
    }

    public String deleteSubTask() {
        return new SQL(){{
            UPDATE("task SET status = false");
            WHERE("id = #{subTaskId}");
        }}.toString();
    }

    public String countIssueInProject() {
        return new SQL() {{
            SELECT("COUNT(*) AS numberOfIssue");
            FROM("sub_task_view");
            WHERE("project_id = #{projectId} AND issue NOTNULL");
        }}.toString();
    }

    public String filterIssueInSubTask() {
        return new SQL() {{
            SELECT("id, username, issue, message");
            FROM("sub_task_view");
            WHERE("project_id = #{projectId} AND issue NOTNULL");
        }}.toString();
    }

    public String insertAllSubTaskByParentTaskId() {
        return new SQL(){{
            INSERT_INTO("task");
            VALUES("name, percentage, start_date, end_date, handler_id, project_id, parent_task_id, priority","#{subTaskName},#{percentage},#{startDate},#{endDate},#{handlerId},#{projectId},#{parentTaskId},#{priority}");
        }}.toString();
    }

    public String updateSubTaskToResolveIssue() {
        return new SQL(){{
            UPDATE("task");
            SET("issue = NULL");
            WHERE("id = #{subTaskId}");
        }}.toString();
    }

    public String findAllIssueByParentTaskId() {
        return new SQL(){{
            SELECT("id, username, issue");
            FROM("sub_task_view");
            WHERE("parent_task_id = #{parentTaskId} AND issue NOTNULL");
        }}.toString();
    }

}
