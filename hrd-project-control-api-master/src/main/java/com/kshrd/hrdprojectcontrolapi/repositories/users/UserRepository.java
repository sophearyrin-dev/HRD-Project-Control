package com.kshrd.hrdprojectcontrolapi.repositories.users;
import com.kshrd.hrdprojectcontrolapi.models.hpc.Project;
import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.User;
import com.kshrd.hrdprojectcontrolapi.repositories.users.provider.UserProvider;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserActionDeleteApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserApiRequest;
import com.kshrd.hrdprojectcontrolapi.rest.request.UserUpdateApiResquest;
import com.kshrd.hrdprojectcontrolapi.rest.response.UserApiResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository {

    @Select( "SELECT a.id,a.username,a.password,a.gender,b.id as role_id, b.name as role_name " +
             "from users a left JOIN role b on a.role_id=b.id where a.username=#{username} order by a.id DESC")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "role",column = "role_id",many = @Many(select = "findRolesByUserId")),
    })
    User findByUsername(String username);


    @Select("SELECT r.id, r.name FROM role r WHERE id =#{id}")
    @Results({
        @Result(property = "roleId",column = "id")
    })
    List<Role> findRolesByUserId(int id);


    @InsertProvider(type = UserProvider.class,method = "insertUser")
    @Results({
            @Result(property = "roleId",column = "id"),
            @Result(property = "universityId",column = "university_id"),
            @Result(property = "generationId",column = "generation_id"),
    })
    Boolean insertUser(UserApiRequest userApiRequest);

    @UpdateProvider(type = UserProvider.class,method = "updateUser")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "universityId",column = "university_id"),
            @Result(property = "generationId",column = "generation_id"),
    })
    Boolean updateUser(@Param("userUpdateApiResquest") UserUpdateApiResquest userUpdateApiResquest, int userId);

    @UpdateProvider(type=UserProvider.class,method = "userAction")
    Boolean actionUser(@Param("userActionDelete") UserActionDeleteApiRequest userActionDelete, int userId);

    //find all user by generation id
    @Select("SELECT a.id,a.username,a.status,a.password,a.gender,b.id AS role_id," +
            "b.name AS role_name,c.id AS university_id, c.name AS university_name," +
            "d.id AS generation_id, d.name AS generation_name \n" +
            "FROM users a \n" +
            "LEFT JOIN role b on a.role_id=b.id \n" +
            "LEFT JOIN university c on a.university_id=c.id \n" +
            "LEFT JOIN generation d on a.generation_id=d.id \n" +
            "WHERE a.generation_id =#{generationId} and a.status = TRUE")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "role.roleId",column = "role_id"),
            @Result(property = "role.name",column = "role_name"),
            @Result(property = "university.universityId",column = "university_id"),
            @Result(property = "university.name",column = "university_name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "generation.name",column = "generation_name")
    })
    List<UserApiResponse> findAllUserByGenerationId(@Param("generationId") int generationId);


    //find all user
    @Select("SELECT a.id,a.username,a.status,a.password,a.gender,b.id AS role_id," +
            "b.name AS role_name,c.id AS university_id, c.name AS university_name," +
            "d.id AS generation_id, d.name AS generation_name \n" +
            "FROM users a \n" +
            "LEFT JOIN role b on a.role_id=b.id \n" +
            "LEFT JOIN university c on a.university_id=c.id \n" +
            "LEFT JOIN generation d on a.generation_id=d.id \n" +
            "WHERE a.status = TRUE order by a.id DESC")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "role.roleId",column = "role_id"),
            @Result(property = "role.name",column = "role_name"),
            @Result(property = "university.universityId",column = "university_id"),
            @Result(property = "university.name",column = "university_name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "generation.name",column = "generation_name")
    })
    List<UserApiResponse> findAllUser();


    /**
     * get user by user id
     * @param id
     * @return
     */
    @Select("SELECT a.id,a.username,a.password, a.status,a.password,a.gender,b.id AS role_id,\n" +
            "b.name AS role_name,c.id AS university_id, c.name AS university_name,\n" +
            "d.id AS generation_id, d.name AS generation_name\n" +
            "FROM users a \n" +
            "LEFT JOIN role b on a.role_id=b.id \n" +
            "LEFT JOIN university c on a.university_id=c.id\n" +
            "LEFT JOIN generation d on a.generation_id=d.id\n" +
            "WHERE a.status = TRUE and a.id = #{userId}")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "role.roleId",column = "role_id"),
            @Result(property = "role.name",column = "role_name"),
            @Result(property = "university.universityId",column = "university_id"),
            @Result(property = "university.name",column = "university_name"),
            @Result(property = "generation.generationId",column = "generation_id"),
            @Result(property = "generation.name",column = "generation_name")
    })
    UserApiResponse findOne(int id);

}



