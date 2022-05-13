package com.task.permission.model;

public enum Role {
    ROLE_EMPLOYEE("ÇALIŞAN"),ROLE_MANAGER("YÖNETİCİ");

    private final String displayText;

    Role(String displayText) {
        this.displayText = displayText;
    }
    public String getDisplayText(){
        return displayText;
    }
}
