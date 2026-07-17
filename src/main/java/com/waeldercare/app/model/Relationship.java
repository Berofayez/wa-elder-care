package com.waeldercare.app.model;

public enum Relationship {
    SELF("I am the person who needs care"),
    FAMILY_MEMBER("I'm asking on behalf of a family member");

    private final String label;

    Relationship(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
