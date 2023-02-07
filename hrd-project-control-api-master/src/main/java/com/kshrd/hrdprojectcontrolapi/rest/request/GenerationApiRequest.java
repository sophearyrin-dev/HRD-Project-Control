package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GenerationApiRequest {
    @JsonIgnore
    private int generationId;
    private String name;

    public GenerationApiRequest() {
    }

    public GenerationApiRequest(int generationId, String name) {
        this.generationId = generationId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenerationId() {
        return generationId;
    }

    public void setGenerationId(int generationId) {
        this.generationId = generationId;
    }

    @Override
    public String toString() {
        return "GenerationApiRequest{" +
                "generationId=" + generationId +
                ", name='" + name + '\'' +
                '}';
    }
}
