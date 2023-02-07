package com.kshrd.hrdprojectcontrolapi.rest.response;

public class TaskProgressApiResponse {

    private float progress;

    public TaskProgressApiResponse() {}

    public TaskProgressApiResponse(float progress) {
        this.progress = progress;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "TaskProgressApiResponse{" +
                "progress=" + progress +
                '}';
    }
}
