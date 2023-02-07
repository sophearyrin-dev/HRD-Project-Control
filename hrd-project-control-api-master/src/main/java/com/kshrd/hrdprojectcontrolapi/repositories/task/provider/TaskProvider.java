package com.kshrd.hrdprojectcontrolapi.repositories.task.provider;

import com.kshrd.hrdprojectcontrolapi.models.hpc.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TaskProvider {

    public String findProgressOfAllMainTaskByProjectId() {
        return new SQL(){{
            SELECT("parent_task_id, parent_task_name, main_task_percentage, start_date, end_date, number_of_issue");
            FROM("task_progress_in_project_view");
            WHERE("project_id = #{filter.projectId}");
        }}.toString();
    }

    public String updateMainTaskHandler(@Param("filter") TaskUpdateFilter taskUpdateFilter) {
        return new SQL(){{
            UPDATE("task SET handler_id = #{taskUpdateApiRequest.handlerId}");
            WHERE("id = #{filter.parentTaskId}");
        }}.toString();
    }

    public String findAllDocumentTaskPercentage(@Param("filter") TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter) {
        return new SQL(){{
            SELECT("project_id, document_task_percentage");
            FROM("document_task_view");
            if (taskProgressOfAllProjectFilter.getGenerationId() != null && taskProgressOfAllProjectFilter.getCourseId() != null) {
                WHERE("generation_id = #{filter.generationId} AND course_id = #{filter.courseId}");
            }
        }}.toString();
    }

    public String findTaskProgressOfAllProject(@Param("filter") TaskProgressOfAllProjectFilter taskProgressOfAllProjectFilter) {
        return new SQL(){{
            SELECT("project_id, project_name, SUM(base_percentage) AS total_base_task_percentage, SUM(task_progress) AS project_progress_percentage, generation_name, course_name");
            FROM("task_project_progress_of_all_project_view");
            if (taskProgressOfAllProjectFilter.getGenerationId() != null && taskProgressOfAllProjectFilter.getCourseId() != null) {
                WHERE("generation_id = #{filter.generationId} AND course_id = #{filter.courseId} AND maintask = base_maintask");
                GROUP_BY("project_id, project_name, generation_name, course_name");
            }
        }}.toString();
    }

    public String countIssueToShowInProject() {
        return new SQL() {{
            SELECT("COUNT(*) AS numberOfIssue");
            FROM("sub_task_view");
            WHERE("project_id = #{projectId} AND issue NOTNULL");
        }}.toString();
    }

    public String findAllTaskProgressByProjectId() {
        return new SQL(){{
            SELECT("project_id, project_name, SUM(base_percentage) AS total_base_task_percentage, SUM(task_progress) AS project_progress_percentage, generation_name, course_name");
            FROM("task_project_progress_of_all_project_view");
            WHERE("project_id = #{projectId}");
            GROUP_BY("project_id, project_name, generation_name, course_name");
        }}.toString();
    }

    public String findDocumentTaskPercentageByProjectId() {
        return new SQL(){{
            SELECT("project_id, document_task_percentage");
            FROM("document_task_view");
            WHERE("project_id = #{projectId}");
        }}.toString();
    }

}
