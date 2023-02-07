package com.kshrd.hrdprojectcontrolapi.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskUpdateApiRequest {

    private int handlerId;

    public TaskUpdateApiRequest() {}

    public TaskUpdateApiRequest(int handlerId) {
        this.handlerId = handlerId;
    }

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
    }

}
