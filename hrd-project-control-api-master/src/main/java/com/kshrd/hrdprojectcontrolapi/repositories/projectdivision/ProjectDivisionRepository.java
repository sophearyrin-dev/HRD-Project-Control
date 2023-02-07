//package com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision;
//import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
//import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectDivision;
//import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;
//import com.kshrd.hrdprojectcontrolapi.rest.response.*;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public interface ProjectDivisionRepository {
//
//    @Select( "SELECT pd.project_division_id as id, u.id as user_id, u.username, u.gender, un.id as university_id, un.name as university_name, r.id as role_id ,r.name as role_name, g.id as generation_id, g.name as generation_name\n" +
//            "from project_division pd \n" +
//            "INNER JOIN users u on pd.user_id = u.id\n" +
//            "INNER JOIN role r on u.role_id = r.id\n" +
//            "INNER JOIN university un on u.university_id = un.id\n" +
//            "INNER JOIN generation g on u.generation_id = g.id\n" +
//            "where pd.project_id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "user.userId", column = "user_id"),
//            @Result(property = "user.username", column = "username"),
//            @Result(property = "user.gender", column = "gender"),
//            @Result(property = "user.university.universityId", column = "university_id"),
//            @Result(property = "user.university.name", column = "university_name"),
//            @Result(property = "user.role.roleId", column = "role_id"),
//            @Result(property = "user.role.name", column = "role_name"),
//            @Result(property = "user.generation.generationId", column = "generation_id"),
//            @Result(property = "user.generation.name", column = "generation_name")
//    })
//    List<ProjectDivisionApiResponse> findAllUserByProjectId(int id);
//
//    @Select("select p.id, p.name , p.objective, p.feature\n" +
//            "from project_division pd \n" +
//            "inner JOIN project p on pd.project_id = p.id\n" +
//            "where pd.project_id = #{id} limit 1")
//    @Results({
//            @Result(property = "projectId", column = "id"),
//    })
//    List<Project> findProjectDivisionByProjectId(int id);
//
//    //get by user id with project division ,project schedule,docuement schedule,generation,course
//
//    @Select("SELECT pd.project_schedule_id,pd.document_schedule_id, p.id,p.name AS project_name,g.name AS generation_name,g.id as generations_id,c.id AS courses_id, c.name AS course_name " +
//            "from project_division pd " +
//            "inner JOIN project p ON pd.project_id = p.id " +
//            "INNER JOIN generation g ON g.id=p.generation_id " +
//            "INNER JOIN course c ON c.id=p.course_id " +
//            "WHERE pd.user_id =#{id}")
//    @Results({
//            @Result(property = "projectId", column = "id"),
//            @Result(property = "name", column = "project_name"),
//            @Result(property = "projectScheduleId", column = "project_schedule_id"),
//            @Result(property = "documentScheduleId", column = "document_schedule_id"),
//            @Result(property = "generation", column = "generation_name"),
//            @Result(property = "course", column = "course_name"),
//            @Result(property = "generationId", column = "generations_id"),
//            @Result(property = "courseId", column = "courses_id"),
//    })
//    List<ProjectUserApiResponse> findProjectDivisionByUserId(int id);
//
//    @InsertProvider(type = ProjectDivisionProvider.class, method = "insertUserDivision")
//    Boolean insertUserDivision(ProjectDivision projectDivision);
//
//    @Select("select ds.id as document_schedule_id, ps.id as project_schedule_id\n" +
//            "from schedule_type st \n" +
//            "inner join document_schedule ds on st.id = ds.schedule_type_id\n" +
//            "inner join project_schedule ps on st.id = ps.schedule_type_id\n" +
//            "WHERE st.id = #{scheduleTypeId}")
//    @Results({
//            @Result(property = "projectScheduleId", column = "document_schedule_id"),
//            @Result(property = "documentScheduleId", column = "project_schedule_id")
//    })
//    ScheduleTypeFilterDocumentAndProject documentAndProjects(int scheduleTypeId);
//
//    @SelectProvider(type = ProjectDivisionProvider.class, method = "findAllProjectMainTask")
//    @Results({
//            @Result(property = "taskName", column = "main_task_name"),
//            @Result(property = "startDate", column = "start_date"),
//            @Result(property = "endDate", column = "end_date")
//    })
//    List<ProjectMainTaskApiResponse> findAllProjectMainTask(@Param("scheduleTypeId") int scheduleTypeId);
//
//    @SelectProvider(type = ProjectDivisionProvider.class, method = "findAllIdOfParentTaskByProjectId")
//    List<Integer> findAllIdOfParentTaskByProjectId(@Param("projectId") int projectId);
//
//    @InsertProvider(type = ProjectDivisionProvider.class, method = "insertDefaultSubTaskToEachParentByProjectId")
//    Boolean insertDefaultSubTaskToEachParentByProjectId(@Param("defaultSubTaskName") String defaultSubTaskName, @Param("percentage") int percentage, @Param("parentTaskId") int parentTaskId, @Param("projectId") int projectId);
//
//    @SelectProvider(type = ProjectDivisionProvider.class, method = "findAllDocumentTask")
//    @Results({
//            @Result(property = "taskName", column = "document_task_name"),
//            @Result(property = "startDate", column = "start_date"),
//            @Result(property = "endDate", column = "end_date")
//    })
//    List<DocumentTaskApiResponse> findAllDocumentTask(@Param("scheduleTypeId") int scheduleTypeId);
//
//    @SelectProvider(type = ProjectDivisionProvider.class, method = "findDocumentTaskDeadlineByScheduleTypeId")
//    @Results({
//            @Result(property = "startDate", column = "start_date"),
//            @Result(property = "endDate", column = "end_date")
//    })
//    DocumentTaskDeadlineApiResponse findDocumentTaskDeadlineByScheduleTypeId(@Param("scheduleTypeId") int scheduleTypeId);
//
//    @SelectProvider(type = ProjectDivisionProvider.class, method = "findIdOfDocumentTaskByProjectId")
//    int findIdOfDocumentTaskByProjectId(@Param("projectId") int projectId);
//
//    // TODO: This method is used for insert main task to each project
//    @InsertProvider(type = ProjectDivisionProvider.class, method = "insertMainTask")
//    Boolean insertMainTask(@Param("taskName") String taskName, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("projectId") int projectId);
//
//    @InsertProvider(type = ProjectDivisionProvider.class, method = "insertDocumentSubTask")
//    Boolean insertDocumentSubTask(@Param("taskName") String taskName, @Param("percentage") int percentage, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("parentTaskId") int parentTaskId, @Param("projectId") int projectId);
//
//}

package com.kshrd.hrdprojectcontrolapi.repositories.projectdivision;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ProjectDivision;
import com.kshrd.hrdprojectcontrolapi.models.hpc.ScheduleTypeFilterDocumentAndProject;
import com.kshrd.hrdprojectcontrolapi.rest.response.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectDivisionRepository {

    @Select( "SELECT pd.project_division_id as id, u.id as user_id, u.username, u.gender, un.id as university_id, un.name as university_name, r.id as role_id ,r.name as role_name, g.id as generation_id, g.name as generation_name\n" +
            "from project_division pd \n" +
            "INNER JOIN users u on pd.user_id = u.id\n" +
            "INNER JOIN role r on u.role_id = r.id\n" +
            "INNER JOIN university un on u.university_id = un.id\n" +
            "INNER JOIN generation g on u.generation_id = g.id\n" +
            "where pd.project_id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user.userId", column = "user_id"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.gender", column = "gender"),
            @Result(property = "user.university.universityId", column = "university_id"),
            @Result(property = "user.university.name", column = "university_name"),
            @Result(property = "user.role.roleId", column = "role_id"),
            @Result(property = "user.role.name", column = "role_name"),
            @Result(property = "user.generation.generationId", column = "generation_id"),
            @Result(property = "user.generation.name", column = "generation_name")
    })
    List<ProjectDivisionApiResponse> findAllUserByProjectId(int id);

    @Select("select p.id, p.name , p.objective, p.feature\n" +
            "from project_division pd \n" +
            "inner JOIN project p on pd.project_id = p.id\n" +
            "where pd.project_id = #{id} limit 1")
    @Results({
            @Result(property = "projectId", column = "id"),
    })
    List<Project> findProjectDivisionByProjectId(int id);

    @Select("SELECT user_id,project_id FROM project_division WHERE project_id=#{proId} AND user_id=#{userId} limit 1")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "user_id"),
    })
    ProjectDivision findOne(int proId,int userId);

    //check project id
    @Select("SELECT project_id FROM project_division WHERE project_id=#{proId} LIMIT 1")
    @Results({

            @Result(property = "projectId", column = "project_id"),
    })
    CheckProject findProjectId(int proId);

    //get by user id with project division ,project schedule,docuement schedule,generation,course
    @Select("SELECT pd.project_schedule_id,pd.document_schedule_id, p.id,p.name AS project_name,g.name AS generation_name,g.id as generations_id,c.id AS courses_id, c.name AS course_name " +
            "from project_division pd " +
            "inner JOIN project p ON pd.project_id = p.id " +
            "INNER JOIN generation g ON g.id=p.generation_id " +
            "INNER JOIN course c ON c.id=p.course_id " +
            "WHERE pd.user_id =#{id} and p.status=true")
    @Results({
            @Result(property = "projectId", column = "id"),
            @Result(property = "name", column = "project_name"),
            @Result(property = "projectScheduleId", column = "project_schedule_id"),
            @Result(property = "documentScheduleId", column = "document_schedule_id"),
            @Result(property = "generation", column = "generation_name"),
            @Result(property = "course", column = "course_name"),
            @Result(property = "generationId", column = "generations_id"),
            @Result(property = "courseId", column = "courses_id"),
    })
    List<ProjectUserApiResponse> findProjectDivisionByUserId(int id);

    @InsertProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "insertUserDivision")
    Boolean insertUserDivision(ProjectDivision projectDivision);

    @Select("select ds.id as document_schedule_id, ps.id as project_schedule_id\n" +
            "from schedule_type st \n" +
            "inner join document_schedule ds on st.id = ds.schedule_type_id\n" +
            "inner join project_schedule ps on st.id = ps.schedule_type_id\n" +
            "WHERE st.id = #{scheduleTypeId}")
    @Results({
            @Result(property = "projectScheduleId", column = "document_schedule_id"),
            @Result(property = "documentScheduleId", column = "project_schedule_id")
    })
    ScheduleTypeFilterDocumentAndProject documentAndProjects(int scheduleTypeId);

    @SelectProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "findAllProjectMainTask")
    @Results({
            @Result(property = "taskName", column = "main_task_name"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    List<ProjectMainTaskApiResponse> findAllProjectMainTask(@Param("scheduleTypeId") int scheduleTypeId);

    @SelectProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "findAllIdOfParentTaskByProjectId")
    List<Integer> findAllIdOfParentTaskByProjectId(@Param("projectId") int projectId);

    @InsertProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "insertDefaultSubTaskToEachParentByProjectId")
    Boolean insertDefaultSubTaskToEachParentByProjectId(@Param("defaultSubTaskName") String defaultSubTaskName, @Param("percentage") int percentage, @Param("parentTaskId") int parentTaskId, @Param("projectId") int projectId);

    @SelectProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "findAllDocumentTask")
    @Results({
            @Result(property = "taskName", column = "document_task_name"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    List<DocumentTaskApiResponse> findAllDocumentTask(@Param("scheduleTypeId") int scheduleTypeId);

    @SelectProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "findDocumentTaskDeadlineByScheduleTypeId")
    @Results({
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    DocumentTaskDeadlineApiResponse findDocumentTaskDeadlineByScheduleTypeId(@Param("scheduleTypeId") int scheduleTypeId);

    @SelectProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "findIdOfDocumentTaskByProjectId")
    int findIdOfDocumentTaskByProjectId(@Param("projectId") int projectId);

    // TODO: This method is used for insert main task to each project
    @InsertProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "insertMainTask")
    Boolean insertMainTask(@Param("taskName") String taskName, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("projectId") int projectId);

    @InsertProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "insertDocumentSubTask")
    Boolean insertDocumentSubTask(@Param("taskName") String taskName, @Param("percentage") int percentage, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("parentTaskId") int parentTaskId, @Param("projectId") int projectId);


    /**
     * Delete user in project division
     * @param Project_division_id
     * @return
     */
    @DeleteProvider(type = com.kshrd.hrdprojectcontrolapi.repositories.ProjectDivision.ProjectDivisionProvider.class, method = "deleteProjectDivision")
    Boolean deleteProjectDivision(int Project_division_id);
}
