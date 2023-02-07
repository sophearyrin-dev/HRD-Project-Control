package com.kshrd.hrdprojectcontrolapi.rest.message;

public enum  Resources {

    USER("User"),
    PROJECT("Project"),
    TASK("Task"),
    SUBTASK("Subtask"),
    PROJECT_SCHEDULE_MAIN_TAK("Main Task of Project Schedule"),
    DOCUMENT_SCHEDULE_MAIN_TAK(" Main Task of Document Schedule"),
    DAILYREPORT("Daily Report");
    private String name;

    Resources(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
