package com.kshrd.hrdprojectcontrolapi.rest.response;

import com.kshrd.hrdprojectcontrolapi.models.users.Generation;
import com.kshrd.hrdprojectcontrolapi.models.users.Role;
import com.kshrd.hrdprojectcontrolapi.models.users.University;

public class UserApiResponse {

    private int userId;
    private String username;
    private String password;
    private String gender;
    private Boolean status;
    Role role;
    University university;
    Generation generation;

    public UserApiResponse() {
    }

    public UserApiResponse(int userId, String username, String password, String gender, Boolean status, Role role, University university, Generation generation) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.role = role;
        this.university = university;
        this.generation = generation;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "UserApiResponse{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", university=" + university +
                ", generation=" + generation +
                '}';
    }
}
