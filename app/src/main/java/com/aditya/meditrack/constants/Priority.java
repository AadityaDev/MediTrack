package com.aditya.meditrack.constants;

public enum Priority {

    NORMAL("normal"),
    URGENT("urgent"),
    EMERGENCY("emergency");

    private String priority;

    Priority(String priority){
        this.priority=priority;
    }

    public String getPriority() {
        return priority;
    }

}
