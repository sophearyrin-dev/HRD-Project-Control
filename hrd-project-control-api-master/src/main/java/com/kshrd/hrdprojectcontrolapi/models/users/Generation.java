package com.kshrd.hrdprojectcontrolapi.models.users;

public class Generation {

    private int generationId;
    private String name;
    private Boolean status;

    public Generation() {
    }

    public Generation(int generationId, String name, Boolean status) {
        this.generationId = generationId;
        this.name = name;
        this.status = status;
    }

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Generation{" +
                "generationId=" + generationId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

