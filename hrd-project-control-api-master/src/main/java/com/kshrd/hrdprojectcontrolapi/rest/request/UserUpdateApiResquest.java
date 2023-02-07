package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.University;

public class UserUpdateApiResquest {

    @JsonIgnore
    private int userId;
    private String username;
    private String password;
    private String gender;
    private int roleId;
    private int universityId;
    private int generationId;

    public UserUpdateApiResquest() {
    }

    public UserUpdateApiResquest(int userId, String username, String password, String gender, int roleId, int universityId, int generationId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.roleId = roleId;
        this.universityId = universityId;
        this.generationId = generationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    @Override
    public String toString() {
        return "UserUpdateApiResquest{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", roleId=" + roleId +
                ", universityId=" + universityId +
                ", generationId=" + generationId +
                '}';
    }
}
